package com.java.service;

import com.java.domian.Architect;
import com.java.domian.Designer;

/**
 * 
 * @Description: CompanyService��TeamService��ֱ��ø��Ե�����������˾Ա���Ϳ����Ŷӳ�Ա����
 * @author  karlieswift
 * @date 2020��3��29��
 * @version "13.0.1"
 */

import com.java.domian.Employee;
import com.java.domian.Programmer;
import com.java.domian.Status;

public class TeamService {

	private static int count = 1;// ��memberid��ֵ
	private final int MAX_MEMBER = 5;
	private Programmer[] team = new Programmer[MAX_MEMBER];// ���濪����Ա
	private int total;// ��¼�������е�ʵ������

	/**
	 * 
	 * @Function ��ȡ��������ĳ�Ա
	 */
	public Programmer[] getTeam() {
		Programmer[] team = new Programmer[total];
		for (int i = 0; i < team.length; i++) {
			team[i] = this.team[i];
		}
		return team;
	}

	/**
	 * 
	 * @throws TeamException 
	 * @Function @param ��ָ����Ա����ӵ������Ŷ���
	 */
	public void addMember(Employee e) throws TeamException {
//		 * ��Ա�������޷����
		if(total>=MAX_MEMBER) {
			throw new TeamException("��Ա����");
		}
//		 * �ó�Ա���ǿ�����Ա�� �޷���� 
		if(!(e instanceof Programmer) ){
			throw new TeamException("�ó�Ա���ǿ�����Ա�� �޷���� ");
			
		}
//		 * ��Ա�����Ŷӳ�Ա 
		if(isExist(e)) {
			throw new TeamException("��Ա�����Ŷӳ�Ա  ");		
		}
		//�Ѿ�����������ĳ�Ա
		Programmer p=(Programmer)e;
		if("BUSY".equals(p.getStatus().getNAME())){
			throw new TeamException("��Ա�Ѿ�����������ĳ�Ա  ");		
		}else if("VOCATION".equals(p.getStatus().getNAME())){
			throw new TeamException("��Ա���ݼ�  ");		
		}

		
		//���Ȼ�ȡteam�Ѿ��еĳ�Ա�ܹ�ʦ�����ʦ,����Ա����
		int numOfArch=0,numOfDes=0,numOfPro=0;
		for(int i=0;i<total;i++) {
			if(team[i] instanceof Architect) {
				numOfArch++;
			}else if(team[i] instanceof Designer) {
				numOfDes++;
			}else if(team[i] instanceof Programmer) {
				numOfPro++;
			}
		}
		
		if(p instanceof Architect) {
			if(numOfArch>=1) {
				throw new TeamException("�Ŷ���ֻ����һ���ܹ�ʦ   ");		
			}
		}else if(p instanceof Designer) {
			if(numOfDes>=2) {
				throw new TeamException("�Ŷ���ֻ�����������ʦ");	
			}
		}
			else if(p instanceof Programmer) {
				if(numOfPro>=3) {
					throw new TeamException("�Ŷ���ֻ������������Ա");			
				}
			}
		
		
		//��p��ӵ�team��
		team[total]=p;
		total++;
		
		//p������ֵstatus
		p.setStatus(Status.BUSY);
		p.setMemberid(count++);
	}

	/**
	 * 
	 * @throws TeamException 
	 * @Function @param ɾ��ָ����Ա��
	 */
	public void removeMember(int memberid) throws TeamException {

		int i;
		for(i=0;i<total;i++) {
			if(team[i].getMemberid()==memberid) {
				team[i].setStatus(Status.FREE);
				break;
			}
		}
		//δ�ҵ�id
		if(i==total) {
			throw new TeamException("û�иó�Ա");
		}
		for(int j=i+1;j<total;j++) {
			team[j-1]=team[j];
		}
		team[total-1]=null;
		total--;
	}
	/**
	 * �ж�ָ��Ա���Ƿ��ڿ���������
	 * @Function @param e
	 * @Function @return
	 */
	private  boolean isExist(Employee e){
	
		for(int i=0;i<total;i++) {
			if(e.getId()==team[i].getId()) {
				return true;
			}
		}
		return false;
	}
}
























