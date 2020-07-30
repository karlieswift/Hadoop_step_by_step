package com.java.view;

import com.java.domian.Employee;
import com.java.domian.Programmer;

/**
 * 
 * @Description: TeamView模块为主控模块，负责菜单的显示和处理用户操作
 * @author  karlieswift
 * @date 2020年3月29日
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
			System.out.print("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4) : ");
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
				System.out.println("是否要退出(Y/N)");
				char isexit=TSUtility.readConfirmSelection();
				if(isexit=='Y') {
					loop = false;
					System.out.println("退出程序");
				}
				break;
			}

			}
		}
	}

	/**
	 * @Function 显示所有员工信息
	 */
	private void listAllEmployee() {
	//	System.out.println("显示所有员工信息");
		System.out.println("-------------------------------------开发团队调度软件--------------------------------------");
		
		Employee []employees=listSvc.getAllEmployees();
		if(employees==null ||employees.length==0) {
			System.out.println("没有任何员工");
			return;
		}
		System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备");
		for(int i=0;i<employees.length;i++) {
			System.out.println(employees[i]);
		}
		System.out.println("-------------------------------------开发团队调度软件--------------------------------------");
		
	}

	private void listTeam() {
		System.out.println("--------------------------------------团队成员----------------------------------------------");
		Programmer []team=teamSvc.getTeam();
		if(team==null ||team.length==0) {
			System.out.println("团队没有任何员工");
			return;
		}
		System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t状态\t奖金");
		for(int i=0;i<team.length;i++) {
			System.out.println(team[i].getDatailsTeam());
		}
		System.out.println("---------------------------------------------------------------------------------------------");

		
	}

	private void addMember() {
		System.out.println("---------------------添加成员----------------------------------------------------------------");
		System.out.print("请输入要添加的员工ID : ");
		System.out.println("添加成员");
		int id=TSUtility.readInt();
		try {
			Employee employee=listSvc.getEmployee(id);
			teamSvc.addMember(employee);
			System.out.println("添加成功");
		} catch (TeamException e) {
			 
			System.out.println("添加失败,原因 "+e.getMessage());
		}
		//按回车键继续......
		TSUtility.readReturn();
	}

	private void delteMember() {
		System.out.println("---------------------删除成员------------------------------------------------------------------");
		System.out.print("请输入要删除的成员TID : ");
		int id=TSUtility.readInt();
		System.out.println("是否要删除(Y/N)");
		char isdelete=TSUtility.readConfirmSelection();
		if(isdelete=='N') {
			return;
		}
		
		try {
			teamSvc.removeMember(id);
			System.out.println("删除成功");
		} catch (TeamException e) {
			System.out.println("删除失败 ，原因 "+e.getMessage());
		}
		
		//按回车键继续......
		TSUtility.readReturn();
		
	}

	public static void main(String[] args) {
		TeamView view = new TeamView();
		view.enterMainMeun();
	}

}








