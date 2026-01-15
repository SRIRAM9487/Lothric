export interface Message {
  id: string;
  text: string;
  sender: string;
  time: string;
  type: "sent" | "received";
  reactions?: Record<string, number>;
}

export interface UserEntity {
  id: number;
  name: string;
  username: string;
  email: string;
  role: string;
}
