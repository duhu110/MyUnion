package com.qhduhu.myunion.entity;

public class JfEntity {
	 	public int _id;  
	    public String yg_name;  
	    public String jf_descrp;  
	    public String jf_type;  
	    public String jf_typedescrp;  
	    public byte[] jf_pic1;
	    public byte[] jf_pic2;
	    public int jf;
	    

	    public JfEntity() {
		}

		public JfEntity( String yg_name, String jf_descrp,
				String jf_type, String jf_typedescrp, byte[] jf_pic1,
				byte[] jf_pic2, int jf) {
			super();
		
			this.yg_name = yg_name;
			this.jf_descrp = jf_descrp;
			this.jf_type = jf_type;
			this.jf_typedescrp = jf_typedescrp;
			this.jf_pic1 = jf_pic1;
			this.jf_pic2 = jf_pic2;
			this.jf = jf;
		}

		public int get_id() {
			return _id;
		}

		public void set_id(int _id) {
			this._id = _id;
		}

		public String getYg_name() {
			return yg_name;
		}

		public void setYg_name(String yg_name) {
			this.yg_name = yg_name;
		}

		public String getJf_descrp() {
			return jf_descrp;
		}

		public void setJf_descrp(String jf_descrp) {
			this.jf_descrp = jf_descrp;
		}

		public String getJf_type() {
			return jf_type;
		}

		public void setJf_type(String jf_type) {
			this.jf_type = jf_type;
		}

		public String getJf_typedescrp() {
			return jf_typedescrp;
		}

		public void setJf_typedescrp(String jf_typedescrp) {
			this.jf_typedescrp = jf_typedescrp;
		}

		public byte[] getJf_pic1() {
			return jf_pic1;
		}

		public void setJf_pic1(byte[] jf_pic1) {
			this.jf_pic1 = jf_pic1;
		}

		public byte[] getJf_pic2() {
			return jf_pic2;
		}

		public void setJf_pic2(byte[] jf_pic2) {
			this.jf_pic2 = jf_pic2;
		}

		public int getJf() {
			return jf;
		}

		public void setJf(int jf) {
			this.jf = jf;
		}

		
}
