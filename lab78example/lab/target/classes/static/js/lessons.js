var app = angular.module('lessons', []).config(function ($httpProvider) {
    csrftoken = $("meta[name='_csrf']").attr("content");
    csrfheader = $("meta[name='_csrf_header']").attr("content");
    $httpProvider.defaults.headers.common["X-CSRF-TOKEN"] = csrftoken;
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(csrfheader, csrftoken);
    });
});

app.controller("LessonsController", function ($scope, $http) {

    $scope.successGetLessonsCallback = function (response) {
        $scope.lessonsList = response.data;
        $scope.errormessage = "";
    };

    $scope.errorGetLessonsCallback = function (error) {
        console.log(error);
        $scope.errormessage = "Unable to get list of lessons";
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
        $scope.errormessage = "";
    };

    $scope.errorDeleteLessonCallback = function (error) {
        console.log(error);
        if (error.status === 405) {
            $scope.errormessage = "You do not have permissions to do that";
        } else
        if (error.status === 403) {
            $scope.errormessage = "You do not have permissions to do that";
        } else
            $scope.errormessage = "Unable to delete lesson; check if there are any related records exist. Such records should be removed first";
    };

    $scope.deleteLesson = function (id) {
        $scope.deletedId = id;

        $http.delete('/public/rest/lessons/' + id).then($scope.successDeleteLessonCallback, $scope.errorDeleteLessonCallback);
    };


    $scope.successGetLessonCallback = function (response) {
        $scope.lessonsList.splice(0, 0, response.data);
        $scope.errormessage = "";
    };

    $scope.errorGetLessonCallback = function (error) {
        console.log(error);
        $scope.errormessage = "Unable to get information on lesson " + $scope.inputDiscipline;
    };

    $scope.successAddLessonCallback = function (response) {

        $http.get('/public/rest/lessons/' + $scope.inputDiscipline).then($scope.successGetLessonCallback, $scope.errorGetLessonCallback);
        $scope.errormessage = "";
    };

    $scope.errorAddLessonCallback = function (error) {
        console.log(error);
        if (error.status === 405) {
            $scope.errormessage = "You do not have permissions to do that";
        } else
        if (error.status === 403) {
            $scope.errormessage = "You do not have permissions to do that";
        } else
            $scope.errormessage = "Impossible to add new lesson";
    };

    $scope.addLesson = function () {
        const lessonData = {
            discipline: $scope.inputDiscipline,
            lessonType: $scope.inputLessonType,
            audience: $scope.inputAudience,
            address: $scope.inputAddress,
            start: $scope.inputStart,

        };

        $http.post('/public/rest/lessons', lessonData, {
            headers: {
                'Content-Type': 'application/json'
            }
        }).then($scope.successAddLessonCallback, $scope.errorAddLessonCallback);
    };

});
