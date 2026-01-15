import { useState } from "react";

type SidebarProps = {
  userName?: string;
  userRole?: string;
};

function Sidebar({ userName, userRole }: SidebarProps) {
  const [activeTab, setActiveTab] = useState<string>("");

  return (
    <aside className="chat-sidebar">
      <div className="sidebar-user-profile">
        <div className="user-info-wrapper">
          <div className="user-avatar large">
            {userName ? userName.charAt(0).toUpperCase() : ""}
          </div>
          <div className="user-details">
            <p className="user-name-display">{userName ?? "User"}</p>
            <p className="user-role">{userRole ?? "Role"}</p>
          </div>
        </div>
      </div>

      <div className="sidebar-content">
        <div className="nav-section">
          <p className="sidebar-label">Communication</p>
          <div
            className={`channel-item ${activeTab === "Chat" ? "active" : ""}`}
            onClick={() => setActiveTab("Chat")}
          >
            <span className="icon">#</span> project-alpha
          </div>
        </div>

        <div className="nav-section">
          <p className="sidebar-label">Assets</p>
          <div
            className={`channel-item ${
              activeTab === "Inventory" ? "active" : ""
            }`}
            onClick={() => setActiveTab("Inventory")}
          >
            <span className="icon">📂</span> Inventory
          </div>
        </div>
      </div>

      <div className="sidebar-footer">
        <button className="logout-button">Logout</button>
      </div>
    </aside>
  );
}

export default Sidebar;
