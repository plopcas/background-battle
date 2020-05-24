var monetizationId = "";
$(document).ready(function () {
    $("#create-tournament-btn").click(function() {
        createTournament();
    });

    $("#join-tournament-btn").click(function() {
       joinTournament();
    });

    if (document.monetization) {
         document.monetization.addEventListener('monetizationstart', (event) => {
            // showExclusiveContent()
            monetizationId = event.detail.requestId;
         })
    }

    if (!document.monetization) {
        //showCTA()
    } else {
        //showLoading()
    }
});

function createTournament () {
    $.ajax({
        beforeSend: function(request) {
            request.setRequestHeader("Web-Monetization-Id", monetizationId);
        },
        url: "/api/v1/tournaments/create",
        type: "POST",
        contentType: "application/json;charset=utf-8",
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
    var tournamentId = $("#tournament-id").val().trim();
    $.ajax({
        url: "/api/v1/tournaments/" + tournamentId + "/join",
        type: "POST",
        contentType: "application/json;charset=utf-8",
        data: username,
        success: function (response) {
            window.location.href = "tournament/" + response.id;
        },
        error: function (err) {
            console.log(err);
            if (err.status === 400) {
                alert("The tournament is full, sorry. Create a new instead?")
            }
        }
    });
}

function showExclusiveContent () {
  document.getElementById('exclusive').classList.remove('hidden')
  document.getElementById('loading').classList.add('hidden')
  document.getElementById('cta').classList.add('hidden')
}

function showCTA () {
  document.getElementById('loading').classList.add('hidden')
  document.getElementById('cta').classList.remove('hidden')
}

function showLoading () {
  document.getElementById('loading').classList.remove('hidden')
}
