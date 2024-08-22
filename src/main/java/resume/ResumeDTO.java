package resume;

import lombok.Data;

@Data
public class ResumeDTO {
	// Data Model
	int key;
	//basic
	String email;
	String name;
	String mname;
	String ename;
	String birth;
	String phone;
	String z1;
	String z2;
	String z3;
	String z4;
	String z5;
	//edu
	String h1;
	String h2;
	String h3;
	String h4;
	String h5;
	//exp
	String wloc;
	String wrank;
	String mwork;
	String swork;
	String ework;
	//qua
	String dqua;
	String nqua;
	String rqua;
	String pqua;
	//hope
	String jhope;
	String phope;
	String lhope;
	String presv;
	//ext
	String dact;
	String cact;
	String nact;
	String hact;
}
