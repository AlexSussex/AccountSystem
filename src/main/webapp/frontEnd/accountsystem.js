var app = angular.module('accountSystem', []);
app.controller('accountController', function($scope, $http) {
	$scope.firstName = null;
	$scope.secondName = null;
	$scope.accountNumber = null;
	$scope.id = null;
	$scope.updatefName=null;
	$scope.updatelName=null;
	$scope.updateANumber=null;
	$scope.lblMsg = null;

	//Call the services
	$http.post('http://localhost:8080/accountSystem/rest/account/json', JSON.stringify(data)).then(function (reply) {
	if (reply.data)
	$scope.msg = "Data submitted";
	}, function (reply) {
	$scope.msg = "Service not available";
	$scope.statusval = reply.status;
	$scope.statustext = reply.statusText;
	$scope.headers = reply.headers();
	});
};
   $http({
        method : "GET",
        url : "http://localhost:8080/accountSystem/rest/account/json"
    }).then(function mySuccess(reply) {
        $scope.myAccounts = reply.data;
    }, function myError(reply) {
        $scope.myAccounts = reply.statusText;
    });
});
	