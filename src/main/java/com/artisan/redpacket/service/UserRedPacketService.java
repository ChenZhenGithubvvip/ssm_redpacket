package com.artisan.redpacket.service;

public interface UserRedPacketService {
	
	/**
	 * �����������Ϣ.
	 * @param redPacketId ������
	 * @param userId ������û����
	 * @return Ӱ���¼��.
	 */
	public int grapRedPacket(Long redPacketId, Long userId);
	
}
