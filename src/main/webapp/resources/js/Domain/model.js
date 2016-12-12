/**
 * 
 */
var Model = function(oConnection, oSql) {
	this._oConnection = oConnection;
	this._oSql = oSql;
};

Model.prototype = {
		findAll : function() {},
		findBy : function() {},
		_runQuery : function(sSql, fncallback) {
			this.oConnection.query(sSql, fnCstllback);
		}
};