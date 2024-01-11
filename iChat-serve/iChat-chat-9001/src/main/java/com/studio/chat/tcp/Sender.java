package com.studio.chat.tcp;

/**
 * @Author: BinBin
 * @Date: 2023/06/27/14:04
 * @Description:
 */
import java.net.*;
import java.util.Random;

public class Sender {
    private static final String RECEIVER_IP = "127.0.0.1";
    private static final int RECEIVER_PORT = 12345;
    private static final int WINDOW_SIZE = 5;
    private static final int TIMEOUT = 1000;

    private static DatagramSocket socket;
    private static InetAddress receiverAddress;
    private static int sequenceNumber;
    private static String[] sendBuffer;
    private static Random random;

    public static void main(String[] args) {
        try {
            socket = new DatagramSocket();
            receiverAddress = InetAddress.getByName(RECEIVER_IP);
            sequenceNumber = 0;
            sendBuffer = new String[WINDOW_SIZE];
            random = new Random();

            String testString = generateTestString(500);
            for (char c : testString.toCharArray()) {
                sendData(String.valueOf(c));
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送方负责将测试字符串分解为单个字符，并按顺序发送给接收方。
     * 发送方还负责模拟丢失数据和定时重传的行为。
     * 发送方发送数据时，会生成一个包含序号和字符的字符串，并将其放入发送缓冲区。
     * 当发送缓冲区中的数据达到窗口大小（5）或者已经发送完所有的字符时，发送方执行定时重传，遍历发送缓冲区中的数据并重新发送。
     * 这样可以保证接收方按照正确的顺序接收数据，并且在发生丢失时进行重传。
     * @param data
     * @throws Exception
     */
    private static void sendData(String data) throws Exception {
        if (random.nextDouble() < 0.3) {
            System.out.println("丢包 data: " + data);
            return;
        }

        data = sequenceNumber + ":" + data;

        byte[] sendData = data.getBytes();
        DatagramPacket packet = new DatagramPacket(sendData, sendData.length, receiverAddress, RECEIVER_PORT);

        socket.send(packet);
        System.out.println("发送 data: " + data);

        sendBuffer[sequenceNumber % WINDOW_SIZE] = data;
        sequenceNumber++;

        if (sequenceNumber % WINDOW_SIZE == 0 || sequenceNumber == 500) {
            Thread.sleep(TIMEOUT);

            for (int i = 0; i < WINDOW_SIZE; i++) {
                if (sendBuffer[i] != null) {
                    packet.setData(sendBuffer[i].getBytes());
                    socket.send(packet);
                    System.out.println("重发 data: " + sendBuffer[i]);
                }
            }
        }
    }

    private static String generateTestString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append("ABCDEF");
        }
        return sb.toString();
    }
}
