$(document).ready(function () {
    $("#vote-left-btn").click(function() {
        voteLeft();
    });

    $("#vote-right-btn").click(function() {
       voteRight();
    });
});

function voteLeft () {
    $.ajax({
        url: "/api/v1/tournaments/" + tournamentId + "/rounds/" + roundNumber + "/left",
        type: "POST",
        contentType: "application/json;charset=utf-8",
        data: username,
        success: function (response) {
            window.location.href = "battle-waiting";
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function voteRight () {
    $.ajax({
        url: "/api/v1/tournaments/" + tournamentId + "/rounds/" + roundNumber + "/right",
        type: "POST",
        contentType: "application/json;charset=utf-8",
        data: username,
        success: function (response) {
            window.location.href = "battle-waiting";
        },
        error: function (err) {
            console.log(err);
        }
    });
}
