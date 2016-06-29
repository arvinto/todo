angular.module('todor', [])
  .controller('data', function($scope, $http) {
      $http.get('/user/1/task').success(function(data) {
        console.data;
        $scope.task = data;
      })
});