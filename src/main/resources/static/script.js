angular.module('todor', [])
  .controller('data', function($scope, $http) {

      $http.get('/user/1/task').success(function(data) {
        console.data;
        $scope.task = data;
      })

      $scope.addTask = function(){
        $http.post('/user/1/task/add', $scope.form).success(function(data){
            console.log("add success");
        });
      };

});