export interface UserInfoType {
  userId: String,
  username: String,
  password: String,
  sex: Number,
  image: String,
  nickname: String,
  status: Number
}
export interface MessageToType {
  id: Number,
  userId: String,
  hand: String,
  status: Number,
  nickname: String,
  toMsg: String,
  sendTime: Date
}
export interface MessageType {
  id: Number,
  post_message: String,
  status: Number,
  send_time: Date,
  from_user: String,
  to_user: String,
  from_user_nickname: String
}