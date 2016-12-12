class User {
	//email
	//phone;
	//point
	//portfolios
	constructor(options = {}) {
		this.id = options.id || null;
		this.name = options.name || null;
		this.realname = options.realname || '';
		this.requester = options.requester || null;
		this.quester = options.quester || null;
		//this.user = [...contents];
	}	
}

export default User;