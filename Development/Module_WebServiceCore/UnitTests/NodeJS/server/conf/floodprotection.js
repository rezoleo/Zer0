var conf={ free_retries: 105, burst_retries: 110,
	   lifetime: 10*60*1000, 
	   minWait: 5*100, maxWait: 9*100 };

conf.waitFunction=function(count){
	return ((conf.maxWait-conf.minWait)/(conf.burst_retries-conf.free_retries)*(count-conf.free_retries))+conf.minWait;
}

module.exports = {
	all       : conf,
	byRoute   : conf,
	byIP      : conf,
	byRouteIP : conf
}
