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
		// ��ǰС���������0
		if (redPacket.getStock() > 0) {
			redPacketDao.decreaseRedPacket(redPacketId);
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
		// ʧ�ܷ���
		return FAILED;
	}

	/**
	 * �ֹ�����������
	 * */
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public int grapRedPacketForVersion(Long redPacketId, Long userId) {
		// ��ȡ�����Ϣ
		RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
		// ��ǰС���������0
		if (redPacket.getStock() > 0) {
			// �ٴδ����̱߳����version��ֵ��SQL�жϣ��Ƿ��������߳��޸Ĺ�����
			int update = redPacketDao.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());
			// ���û�����ݸ��£���˵�������߳��Ѿ��޸Ĺ����ݣ�����������
			if (update == 0) {
				return FAILED;
			}
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
		// ʧ�ܷ���
		return FAILED;
	}

	/**
	 * 
	 * 
	 * �ֹ�������ʱ�������
	 * 
	 * @Description: �ֹ�������ʱ�������
	 * 
	 * @param redPacketId
	 * @param userId
	 * @return
	 * 
	 * @return: int
	 */
	// @Override
	// @Transactional(isolation = Isolation.READ_COMMITTED, propagation =
	// Propagation.REQUIRED)
	// public int grapRedPacketForVersion(Long redPacketId, Long userId) {
	// // ��¼��ʼʱ��
	// long start = System.currentTimeMillis();
	// // ����ѭ�����ȴ��ɹ�����ʱ����100�����˳�
	// while (true) {
	// // ��ȡѭ����ǰʱ��
	// long end = System.currentTimeMillis();
	// // ��ǰʱ���Ѿ�����100���룬����ʧ��
	// if (end - start > 100) {
	// return FAILED;
	// }
	// // ��ȡ�����Ϣ,ע��versionֵ
	// RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
	// // ��ǰС���������0
	// if (redPacket.getStock() > 0) {
	// // �ٴδ����̱߳����version��ֵ��SQL�жϣ��Ƿ��������߳��޸Ĺ�����
	// int update = redPacketDao.decreaseRedPacketForVersion(redPacketId,
	// redPacket.getVersion());
	// // ���û�����ݸ��£���˵�������߳��Ѿ��޸Ĺ����ݣ�����������
	// if (update == 0) {
	// continue;
	// }
	// // �����������Ϣ
	// UserRedPacket userRedPacket = new UserRedPacket();
	// userRedPacket.setRedPacketId(redPacketId);
	// userRedPacket.setUserId(userId);
	// userRedPacket.setAmount(redPacket.getUnitAmount());
	// userRedPacket.setNote("����� " + redPacketId);
	// // �����������Ϣ
	// int result = userRedPacketDao.grapRedPacket(userRedPacket);
	// return result;
	// } else {
	// // һ��û�п�棬�����Ϸ���
	// return FAILED;
	// }
	// }
	// }

	/**
	 * 
	 * 
	 * @Title: grapRedPacketForVersion3
	 * 
	 * @Description: �ֹ���������������
	 * 
	 * @param redPacketId
	 * @param userId
	 * 
	 * @return: int
	 */
	// @Override
	// @Transactional(isolation = Isolation.READ_COMMITTED, propagation =
	// Propagation.REQUIRED)
	// public int grapRedPacketForVersion(Long redPacketId, Long userId) {
	// for (int i = 0; i < 3; i++) {
	// // ��ȡ�����Ϣ��ע��versionֵ
	// RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
	// // ��ǰС���������0
	// if (redPacket.getStock() > 0) {
	// // �ٴδ����̱߳����version��ֵ��SQL�жϣ��Ƿ��������߳��޸Ĺ�����
	// int update = redPacketDao.decreaseRedPacketForVersion(redPacketId,
	// redPacket.getVersion());
	// // ���û�����ݸ��£���˵�������߳��Ѿ��޸Ĺ����ݣ�����������
	// if (update == 0) {
	// continue;
	// }
	// // �����������Ϣ
	// UserRedPacket userRedPacket = new UserRedPacket();
	// userRedPacket.setRedPacketId(redPacketId);
	// userRedPacket.setUserId(userId);
	// userRedPacket.setAmount(redPacket.getUnitAmount());
	// userRedPacket.setNote("����� " + redPacketId);
	// // �����������Ϣ
	// int result = userRedPacketDao.grapRedPacket(userRedPacket);
	// return result;
	// } else {
	// // һ��û�п�棬�����Ϸ���
	// return FAILED;
	// }
	// }
	// return FAILED;
	// }

}
