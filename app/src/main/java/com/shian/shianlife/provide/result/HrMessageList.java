package com.shian.shianlife.provide.result;

import java.util.List;

public class HrMessageList {
	/**
	 * "body":"string", "head":"string", "id":149, "readStatus":6,
	 * "receiver":149, "serverCreateTime":1465545991085, "type":149
	 */
	private List<MessageList> list;
	
	public List<MessageList> getList() {
		return list;
	}

	public void setList(List<MessageList> list) {
		this.list = list;
	}

	public static class MessageList {
		private String body;
		private String head;
		private long id;
		private int readStatus;//1 为未读
		private long receiver;
		private long serverCreateTime;
		private int type;

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}

		public String getHead() {
			return head;
		}

		public void setHead(String head) {
			this.head = head;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public int getReadStatus() {
			return readStatus;
		}

		public void setReadStatus(int readStatus) {
			this.readStatus = readStatus;
		}

		public long getReceiver() {
			return receiver;
		}

		public void setReceiver(long receiver) {
			this.receiver = receiver;
		}

		public long getServerCreateTime() {
			return serverCreateTime;
		}

		public void setServerCreateTime(long serverCreateTime) {
			this.serverCreateTime = serverCreateTime;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

	}
}
