function Inventory() {
  return (
    <section className="inventory-main">
      <header className="chat-header">
        <div className="header-info">
          <h2>Inventory & Assets</h2>
          <p className="status-text">Cloud Storage / Project Alpha</p>
        </div>
      </header>

      <div className="inventory-content">
        {/* Upload Zone */}
        <div
          className="upload-dropzone"
          onClick={() => alert("File picker would open here")}
        >
          <div className="upload-icon">☁️</div>
          <h3>Click or drag files to this area to upload</h3>
          <p>Support for documents, images, and ZIP files</p>
        </div>

        {/* File List Header */}
        <div className="inventory-controls">
          <input
            type="text"
            className="search-bar"
            placeholder="Search files..."
          />
          <button className="primary-btn">New Folder</button>
        </div>

        {/* Simple File Table */}
        <div className="file-list">
          <div className="file-item-header">
            <span>Name</span>
            <span>Size</span>
            <span>Modified</span>
          </div>
          <div className="file-item">
            <span>📄 architecture_proposal.pdf</span>
            <span>2.4 MB</span>
            <span>Today, 10:15 AM</span>
          </div>
          <div className="file-item">
            <span>🖼️ ui_mockups_final.png</span>
            <span>15.8 MB</span>
            <span>Yesterday</span>
          </div>
        </div>
      </div>
    </section>
  );
}

export default Inventory;
