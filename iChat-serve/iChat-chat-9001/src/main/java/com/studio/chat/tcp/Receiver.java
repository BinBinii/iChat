package com.studio.chat.tcp;

import java.net.*;

public class Receiver {
    private static final int RECEIVER_PORT = 12345;

    private static DatagramSocket socket;
    private static byte[] receiveBuffer;
    private static String[] receiveBufferWindow;
    private static int expectedSequenceNumber;

    public static void main(String[] args) {
        try {
            socket = new DatagramSocket(RECEIVER_PORT);
            receiveBuffer = new byte[1024];
            receiveBufferWindow = new String[5];
            expectedSequenceNumber = 0;

            while (true) {
                /**
                 * 在接收方的while循环中，持续接收数据，并根据接收到的数据类型执行相应的操作。
                 * 当接收方接收到完整的测试字符串（500个字符）后，它将输出完整的测试字符串并结束程序。
                 */
                DatagramPacket packet = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(packet);

                String data = new String(packet.getData(), 0, packet.getLength());

                if (data.startsWith("ACK")) {
                    /**
                     * 当接收方收到数据时，它首先检查数据是否以"ACK"开头。如果是以"ACK"开头，
                     * 表示接收方收到了发送方的确认消息。
                     * 接收方解析出ACK消息中的序号，如果序号大于等于期望的序号（即expectedSequenceNumber - 1），
                     * 则滑动窗口并将对应位置的消息置为null，表示已经收到确认。
                     */
                    int ackNumber = Integer.parseInt(data.substring(3));

                    if (ackNumber >= expectedSequenceNumber - 1) {
                        System.out.println("确认 ACK: " + data);
                        slideWindow(ackNumber);
                    }
                } else {
                    /**
                     * 如果数据不是以"ACK"开头，那么它就是发送方发送的数据。
                     * 接收方解析数据中的序号和消息内容。如果序号与期望的序号相等，说明收到了按序发送的数据。
                     * 接收方将消息放入接收缓存窗口中的正确位置，并发送ACK消息给发送方，确认接收成功。
                     */
                    String[] splitData = data.split(":");
                    int sequenceNumber = Integer.parseInt(splitData[0]);
                    String message = splitData[1];

                    if (sequenceNumber == expectedSequenceNumber) {
                        receiveBufferWindow[expectedSequenceNumber % 5] = message;
                        sendAck(expectedSequenceNumber);

                        System.out.println("收到数据: " + message);

                        expectedSequenceNumber++;

                        if (expectedSequenceNumber == 500) {
                            System.out.println("Received complete test string:");
                            StringBuilder sb = new StringBuilder();
                            for (String receivedMessage : receiveBufferWindow) {
                                if (receivedMessage != null) {
                                    sb.append(receivedMessage);
                                }
                            }
                            System.out.println(sb.toString());
                            break;
                        }
                    } else {
                        sendAck(expectedSequenceNumber - 1);
                    }
                }
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendAck(int sequenceNumber) throws Exception {
        String ackMessage = "ACK" + sequenceNumber;
        byte[] sendData = ackMessage.getBytes();
        DatagramPacket packet = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("127.0.0.1"), 12345);

        socket.send(packet);
    }

    private static void slideWindow(int ackNumber) {
        int slideCount = ackNumber - expectedSequenceNumber + 1;
        for (int i = 0; i < slideCount; i++) {
            receiveBufferWindow[(expectedSequenceNumber + i) % 5] = null;
        }
        expectedSequenceNumber += slideCount;
    }
}
