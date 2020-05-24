$("#start-battle-btn").click(function() {
     goToBattle();
 });

function goToBattle() {
    $.ajax({
        url: "/api/v1/tournaments/" + tournamentId + "/start",
        type: "POST",
        contentType: "application/json;charset=utf-8",
        data: username,
        success: function (response) {
            $("#start-battle-btn").addClass("d-none");
            $("#spinner").removeClass("d-none");
        },
        error: function (err) {
            console.log(err);
        }
    });
}
