package com.java.service;

import com.java.domian.Architect;
import com.java.domian.Designer;

/**
 * 
 * @Description: CompanyService和TeamService类分别用各自的数组来管理公司员工和开发团队成员对象
 * @author  karlieswift
 * @date 2020年3月29日
 * @version "13.0.1"
 */

import com.java.domian.Employee;
import com.java.domian.Programmer;
import com.java.domian.Status;

public class TeamService {

	private static int count = 1;// 给memberid赋值
	private final int MAX_MEMBER = 5;
	private Programmer[] team = new Programmer[MAX_MEMBER];// 保存开发成员
	private int total;// 记录开发队中的实际人数

	/**
	 * 
	 * @Function 获取开发队伍的成员
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
	 * @Function @param 将指定的员工添加到开发团队中
	 */
	public void addMember(Employee e) throws TeamException {
//		 * 成员已满，无法添加
		if(total>=MAX_MEMBER) {
			throw new TeamException("成员已满");
		}
//		 * 该成员不是开发人员， 无法添加 
		if(!(e instanceof Programmer) ){
			throw new TeamException("该成员不是开发人员， 无法添加 ");
			
		}
//		 * 该员已是团队成员 
		if(isExist(e)) {
			throw new TeamException("该员已是团队成员  ");		
		}
		//已经是其他队伍的成员
		Programmer p=(Programmer)e;
		if("BUSY".equals(p.getStatus().getNAME())){
			throw new TeamException("该员已经是其他队伍的成员  ");		
		}else if("VOCATION".equals(p.getStatus().getNAME())){
			throw new TeamException("该员在休假  ");		
		}

		
		//首先获取team已经有的成员架构师，设计师,程序员人数
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
				throw new TeamException("团队中只能有一名架构师   ");		
			}
		}else if(p instanceof Designer) {
			if(numOfDes>=2) {
				throw new TeamException("团队中只能有两名设计师");	
			}
		}
			else if(p instanceof Programmer) {
				if(numOfPro>=3) {
					throw new TeamException("团队中只能有三名程序员");			
				}
			}
		
		
		//将p添加到team中
		team[total]=p;
		total++;
		
		//p的属性值status
		p.setStatus(Status.BUSY);
		p.setMemberid(count++);
	}

	/**
	 * 
	 * @throws TeamException 
	 * @Function @param 删除指定的员工
	 */
	public void removeMember(int memberid) throws TeamException {

		int i;
		for(i=0;i<total;i++) {
			if(team[i].getMemberid()==memberid) {
				team[i].setStatus(Status.FREE);
				break;
			}
		}
		//未找到id
		if(i==total) {
			throw new TeamException("没有该成员");
		}
		for(int j=i+1;j<total;j++) {
			team[j-1]=team[j];
		}
		team[total-1]=null;
		total--;
	}
	/**
	 * 判断指定员工是否在开发队伍中
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
























