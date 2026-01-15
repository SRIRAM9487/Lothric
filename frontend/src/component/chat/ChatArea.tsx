import ChatFooter from "./ChatFooter";
import ChatHeader from "./ChatHeader";
import ChatMessage from "./ChatMessage";
import "./ChatArea.css";

function ChatArea() {
  return (
    <main className="chat-area">
      <ChatHeader />
      <ChatMessage />
      <ChatFooter />
    </main>
  );
}

export default ChatArea;
