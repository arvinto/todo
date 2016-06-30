angular.module('todor', [])
  .controller('data', function($scope, $http) {

       var poll = function(){
            $http.get('/user').success(function(data){
                  $http.get('/user/'+data+'/task').success(function(data) {
                    console.data;
                    $scope.task = data;
                  })
              });
            }

      poll();

      $scope.addTask = function(){
          $http.get('/user').success(function(data){
            $http.post('/user/'+data+'/task/add', $scope.form).success(function(data){
                poll();
            });
          });
      };

    $scope.updateTaskStatus = function($event,taskId){
        $http.get('/user').success(function(data){
            var checkbox = $event.target;
            var action = (checkbox.checked?'complete':'reopen');
          $http.put('/user/'+data+'/task/'+taskId+'/'+action, [] ).success(function(data){
              console.log(data['message']);
          });
        });
    };

    $scope.deleteTask = function(taskId){
        $http.get('/user').success(function(data){
          $http.delete('/user/'+data+'/task/'+taskId+'/delete' ).success(function(data){
              console.log(data['message']);
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