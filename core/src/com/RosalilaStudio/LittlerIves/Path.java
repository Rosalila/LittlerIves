package com.RosalilaStudio.LittlerIves;

public enum Path {
	
	C("characters"){
		@Override
		public String getPath(String addToPath) {
			return path+"/"+addToPath;
		}
	},
	I("images"){
		@Override
		public String getPath(String addToPath) {
			return path+"/"+addToPath;
		}
	},
	S("music"){
		@Override
		public String getPath(String addToPath) {
			return path+"/"+addToPath;
		}
	},
	M("levels"){
		@Override
		public String getPath(String addToPath) {
			return path+"/"+addToPath;
		}
	};
	
	public String path;
	
	private Path(String path) {
		this.path=path;
	}
	
	public abstract String getPath(String addToPath);
}
