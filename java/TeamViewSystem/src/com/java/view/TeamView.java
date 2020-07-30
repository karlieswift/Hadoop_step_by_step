package com.java.view;

import com.java.domian.Employee;
import com.java.domian.Programmer;

/**
 * 
 * @Description: TeamViewģ��Ϊ����ģ�飬����˵�����ʾ�ʹ����û�����
 * @author  karlieswift
 * @date 2020��3��29��
 * @version "13.0.1"
 */

import com.java.service.NameListService;
import com.java.service.TSUtility;
import com.java.service.TeamException;
import com.java.service.TeamService;

public class TeamView {

	private NameListService listSvc = new NameListService();
	private TeamService teamSvc = new TeamService();

	public void enterMainMeun() {
		boolean loop = true;
		while (loop) {
			listAllEmployee();
			System.out.print("1-�Ŷ��б�  2-����Ŷӳ�Ա  3-ɾ���Ŷӳ�Ա 4-�˳�   ��ѡ��(1-4) : ");
			char key = TSUtility.readMenuSelection();
			switch (key) {
			case '1': {
				listTeam();
				break;
			}
			case '2': {
				addMember();
				break;
			}

			case '3': {
				delteMember();
				break;
			}
			case '4': {
				System.out.println("�Ƿ�Ҫ�˳�(Y/N)");
				char isexit=TSUtility.readConfirmSelection();
				if(isexit=='Y') {
					loop = false;
					System.out.println("�˳�����");
				}
				break;
			}

			}
		}
	}

	/**
	 * @Function ��ʾ����Ա����Ϣ
	 */
	private void listAllEmployee() {
	//	System.out.println("��ʾ����Ա����Ϣ");
		System.out.println("-------------------------------------�����Ŷӵ������--------------------------------------");
		
		Employee []employees=listSvc.getAllEmployees();
		if(employees==null ||employees.length==0) {
			System.out.println("û���κ�Ա��");
			return;
		}
		System.out.println("ID\t����\t����\t����\tְλ\t״̬\t����\t��Ʊ\t�����豸");
		for(int i=0;i<employees.length;i++) {
			System.out.println(employees[i]);
		}
		System.out.println("-------------------------------------�����Ŷӵ������--------------------------------------");
		
	}

	private void listTeam() {
		System.out.println("--------------------------------------�Ŷӳ�Ա----------------------------------------------");
		Programmer []team=teamSvc.getTeam();
		if(team==null ||team.length==0) {
			System.out.println("�Ŷ�û���κ�Ա��");
			return;
		}
		System.out.println("TID/ID\t����\t����\t����\tְλ\t״̬\t����");
		for(int i=0;i<team.length;i++) {
			System.out.println(team[i].getDatailsTeam());
		}
		System.out.println("---------------------------------------------------------------------------------------------");

		
	}

	private void addMember() {
		System.out.println("---------------------��ӳ�Ա----------------------------------------------------------------");
		System.out.print("������Ҫ��ӵ�Ա��ID : ");
		System.out.println("��ӳ�Ա");
		int id=TSUtility.readInt();
		try {
			Employee employee=listSvc.getEmployee(id);
			teamSvc.addMember(employee);
			System.out.println("��ӳɹ�");
		} catch (TeamException e) {
			 
			System.out.println("���ʧ��,ԭ�� "+e.getMessage());
		}
		//���س�������......
		TSUtility.readReturn();
	}

	private void delteMember() {
		System.out.println("---------------------ɾ����Ա------------------------------------------------------------------");
		System.out.print("������Ҫɾ���ĳ�ԱTID : ");
		int id=TSUtility.readInt();
		System.out.println("�Ƿ�Ҫɾ��(Y/N)");
		char isdelete=TSUtility.readConfirmSelection();
		if(isdelete=='N') {
			return;
		}
		
		try {
			teamSvc.removeMember(id);
			System.out.println("ɾ���ɹ�");
		} catch (TeamException e) {
			System.out.println("ɾ��ʧ�� ��ԭ�� "+e.getMessage());
		}
		
		//���س�������......
		TSUtility.readReturn();
		
	}

	public static void main(String[] args) {
		TeamView view = new TeamView();
		view.enterMainMeun();
	}

}








