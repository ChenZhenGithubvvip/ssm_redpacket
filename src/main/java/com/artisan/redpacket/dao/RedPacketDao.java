package com.artisan.redpacket.dao;

import org.springframework.stereotype.Repository;

import com.artisan.redpacket.pojo.RedPacket;


@Repository
public interface RedPacketDao {
	
	/**
	 * ��ȡ�����Ϣ.
	 * @param id --���id
	 * @return ���������Ϣ
	 */
	public RedPacket getRedPacket(Long id);
	
	/**
	 * �ۼ��������.
	 * @param id -- ���id
	 * @return ���¼�¼����
	 */
	public int decreaseRedPacket(Long id);
	
	
}