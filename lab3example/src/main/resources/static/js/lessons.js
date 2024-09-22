var app = angular.module('lessons', []);

app.controller("LessonsController", function ($scope, $http) {

    $scope.successGetLessonsCallback = function (response) {
        $scope.lessonsList = response.data;
        $scope.errormessage="";
    };

    $scope.errorGetLessonsCallback = function (error) {
        console.log(error);
        $scope.errormessage="Unable to get list of lessons";
    };

    $scope.getLessons = function () {
        $http.get('/public/rest/lessons').then($scope.successGetLessonsCallback, $scope.errorGetLessonsCallback);
    };

    $scope.successDeleteLessonCallback = function (response) {
        for (var i = 0; i < $scope.lessonsList.length; i++) {
            var j = $scope.lessonsList[i];
            if (j.id === $scope.deletedId) {
                $scope.lessonsList.splice(i, 1);
                break;
            }
        }
        $scope.errormessage="";
    };

    $scope.errorDeleteLessonCallback = function (error) {
        console.log(error);
        $scope.errormessage="Unable to delete school; check if there are any related records exist. Such records should be removed first";
    };

    $scope.deleteLesson = function (id) {
        $scope.deletedId = id;
        $http.delete('/public/rest/lessons/' + id).then($scope.successDeleteLessonCallback, $scope.errorDeleteLessonCallback);
    };


    $scope.successGetLessonCallback = function (response) {
        $scope.lessonsList.splice(0, 0, response.data);
        $scope.errormessage="";
    };

    $scope.errorGetLessonCallback = function (error) {
        console.log(error);
        $scope.errormessage="Unable to get information on school number "+$scope.inputnumber;
    };

    $scope.successAddLessonCallback = function (response) {
        $http.get('/public/rest/lessons/discipline/' + $scope.inputDiscipline).then($scope.successGetSchoolCallback, $scope.errorGetSchoolCallback);
        $scope.errormessage="";
    };

    $scope.errorAddLessonCallback = function (error) {
        console.log(error);
        $scope.errormessage="Impossible to add new lesson";
    };

    $scope.addLesson = function () {
        const lessonData = {
            "discipline": $scope.inputDiscipline,
            "lessonType": $scope.inputLessonType,
            "audience": $scope.inputAudience,
            "address": $scope.inputAddress,
            "start": $scope.inputStart
          };
        $http.post('/public/rest/lessons', lessonData).then($scope.successAddSchoolCallback, $scope.errorAddSchoolCallback);
    };

});
