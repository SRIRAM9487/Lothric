import Sidebar from "../sidebar/Sidbar";
import ChatArea from "../../component/chat/ChatArea";
import Inventory from "../../component/inventory/Inventory";

export default function AuthenticatedLayout() {
  return (
    <div className="app-container">
      <Sidebar userName="TESTER" userRole="BRO" />
      <ChatArea />
      <Inventory />
    </div>
  );
}
