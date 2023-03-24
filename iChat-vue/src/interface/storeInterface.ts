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