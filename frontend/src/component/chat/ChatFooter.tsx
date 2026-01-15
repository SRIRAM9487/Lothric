function ChatFooter() {
  return (
    <section className="chat-main">
      <footer className="chat-footer">
        <form
          className="input-form"
          onSubmit={(e) => {
            e.preventDefault();
          }}
        >
          <textarea
            // value={input}
            // onChange={(e) => setInput(e.target.value)}
            placeholder="Message #project-alpha"
          />
          <button type="submit" className="send-button">
            Send
          </button>
        </form>
      </footer>
    </section>
  );
}

export default ChatFooter;
