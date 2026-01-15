function ChatMessage() {
  return (
    <div className="message-list">
      <div id="chat-window">
        <div className="msg-group received">
          <div className="avatar">JD</div>
          <div className="msg-content">
            <span className="sender-name">Jane Doe</span>
            <div className="bubble">
              I've finalized the client presentation for tomorrow. Any feedback
              on the last slide?
            </div>
            <span className="time">12:30 PM</span>
          </div>
        </div>
      </div>
    </div>
  );
}

export default ChatMessage;
