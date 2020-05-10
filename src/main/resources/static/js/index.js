$(document).ready(function () {
    $('#create-tournament-btn').click(function() {
        createTournament();
    });

   $('#join-tournament-btn').click(function() {
       joinTournament();
   });
});

function createTournament () {
    $.ajax({
        url: '/api/v1/tournaments/create',
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: username,
        success: function (response) {
            window.location.href = "tournament/" + response.id;
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function joinTournament () {
    var tournamentId = $('#tournament-id').val().trim();
    $.ajax({
        url: '/api/v1/tournaments/join/' + tournamentId,
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: username,
        success: function (response) {
            window.location.href = "tournament/" + response.id;
        },
        error: function (err) {
            console.log(err);
        }
    });
}
