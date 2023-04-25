export interface UserInfoType {
  userId: String,
  username: String,
  password: String,
  sex: Number,
  image: any,
  nickname: String,
  status: Number
}
export interface PageInfo {
  pages: Number,
  current: Number
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
  from_user_nickname: String,
  timer_show: boolean
}
export interface ChatGptMessageType {
  post_mesage: String,
  status: Number
}
export interface FriendReqType {
  req: String,
  hand: String,
  status: Number,
  create_time: Date,
  pass_time: Date,
  username: String,
  sex: Number,
  image: String,
  nickname: String,
}
export interface CreateGroupFriend {
  userId: String,
  username: String,
  password: String,
  sex: Number,
  image: any,
  nickname: String,
  status: Number,
  checked: boolean
}
export interface FriendListType {
  title: String,
  list: UserInfoType[]
}
export interface CreateGroupFriendListType {
  title: String,
  list: CreateGroupFriend[]
}
export interface ChatGptQuestion {
  first: boolean,
  question: String
}