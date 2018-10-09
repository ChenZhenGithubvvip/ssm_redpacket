package com.artisan.redpacket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.artisan.redpacket.dao.RedPacketDao;
import com.artisan.redpacket.dao.UserRedPacketDao;
import com.artisan.redpacket.pojo.RedPacket;
import com.artisan.redpacket.pojo.UserRedPacket;
import com.artisan.redpacket.service.UserRedPacketService;

@Service
public class UserRedPacketServiceImpl implements UserRedPacketService {
	
	// private Logger logger =
	// LoggerFactory.getLogger(UserRedPacketServiceImpl.class);
	
	@Autowired
	private UserRedPacketDao userRedPacketDao;

	@Autowired
	private RedPacketDao redPacketDao;

	// ʧ��
	private static final int FAILED = 0;

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public int grapRedPacket(Long redPacketId, Long userId) {
		// ��ȡ�����Ϣ -�������ڳ���
		// RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
		// ��ȡ�����Ϣ -��������ʵ�ַ�ʽ
		RedPacket redPacket = redPacketDao.getRedPacketForUpdate(redPacketId);
		int leftRedPacket = redPacket.getStock();
		// ��ǰС���������0
		if (leftRedPacket > 0) {
			redPacketDao.decreaseRedPacket(redPacketId);
			// logger.info("ʣ��Stock����:{}", leftRedPacket);
			// �����������Ϣ
			UserRedPacket userRedPacket = new UserRedPacket();
			userRedPacket.setRedPacketId(redPacketId);
			userRedPacket.setUserId(userId);
			userRedPacket.setAmount(redPacket.getUnitAmount());
			userRedPacket.setNote("redpacket- " + redPacketId);
			// �����������Ϣ
			int result = userRedPacketDao.grapRedPacket(userRedPacket);
			return result;
		}
		// logger.info("û�к����.....ʣ��Stock����:{}", leftRedPacket);
		// ʧ�ܷ���
		return FAILED;
	}


}
