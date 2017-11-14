$(document).ready(function() {
    $(".deleteModalButton1").click(function() {
        $("#modal .modal-body").html("Etes-vous sûr de vouloir supprimer " + $(this).attr("data-name"));
        $("#deleteModalButton2").attr("href", $(this).attr("data-url") + $(this).attr("data-id"));
    });

    function showActionsArgs(selected) {
        var nbArgs = parseInt(selected.attr("data-nbArgs"));
        $("#actionArguments").html('');
        $("#actionArguments").append('<input type="hidden" name="nbActionArgs" value="' + nbArgs + '">');
        for (var i = 0; i < nbArgs; i++) {
            var name = selected.attr("data-arg" + i + "Name");
            var placeholder = selected.attr("data-arg" + i + "Placeholder");
            if (name == "invisible")
                $("#actionArguments").append('<input name="actionArg' + i + '" type="hidden" value="' + placeholder + '">');
            else
                $("#actionArguments").append('<div class="form-group"><label>' + name + '</label><input name="actionArg' + i + '" type="text" class="form-control" placeholder="' + placeholder + '"></div>');
        }
    }

    function showReactionsArgs(selected) {
        var nbArgs = parseInt(selected.attr("data-nbArgs"));
        $("#reactionArguments").html('');
        $("#reactionArguments").append('<input type="hidden" name="nbReactionArgs" value="' + nbArgs + '">');
        for (var i = 0; i < nbArgs; i++) {
            var name = selected.attr("data-arg" + i + "Name");
            var placeholder = selected.attr("data-arg" + i + "Placeholder");
            if (name == "invisible")
                $("#reactionArguments").append('<input name="reactionArg' + i + '" type="hidden" value="' + placeholder + '">');
            else
                $("#reactionArguments").append('<div class="form-group"><label>' + name + '</label><input name="reactionArg' + i + '" type="text" class="form-control" placeholder="' + placeholder + '"></div>');
        }
    }

    showActionsArgs($("#actionForm select :selected"));
    showReactionsArgs($("#reactionForm select :selected"));

    $("#actionForm select").change(function() {
        showActionsArgs($(this).find(":selected"))
    });

    $("#reactionForm select").change(function() {
        showReactionsArgs($(this).find(":selected"))
    });
});





