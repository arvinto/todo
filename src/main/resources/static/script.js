angular.module('todor', [])
  .controller('data', function($scope, $http) {


      var poll = function(){
            $http.get('/user').success(function(data){
                console.log('/user/'+data+'/task');
              $http.get('/user/'+data+'/task').success(function(data) {
                console.data;
                $scope.task = data;
              })
          });
      }

      $http.get()

      $scope.addTask = function(){
          $http.get('/user').success(function(data){
            console.log('/user/'+data+'/task/add');
            $http.post('/user/'+data+'/task/add', $scope.form).success(function(data){
                poll();
            });
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