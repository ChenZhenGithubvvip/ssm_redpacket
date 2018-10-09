package com.artisan.redpacket.dao;

import org.apache.ibatis.annotations.Param;
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
	
	/**
	 * ��ȡ�����Ϣ. ��������ʵ�ַ�ʽ
	 * 
	 * @param id
	 *            --���id
	 * @return ���������Ϣ
	 */
	public RedPacket getRedPacketForUpdate(Long id);

	/**
	 * @Description: �ۼ��������. �ֹ�����ʵ�ַ�ʽ
	 * 
	 * @param id
	 *            -- ���id
	 * @param version
	 *            -- �汾���
	 * 
	 * @return: ���¼�¼����
	 */
	public int decreaseRedPacketForVersion(@Param("id") Long id, @Param("version") Integer version);
}