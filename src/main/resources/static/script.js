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

$(document).ready(function(){

    $('.collapsible').collapsible({
        accordion : false // A setting that changes the collapsible behavior to expandable instead of the default accordion style
    });

    $("#logout-link").click(function(){
        $("#logout-form").submit();
    });
});