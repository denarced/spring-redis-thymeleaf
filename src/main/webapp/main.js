/**
 * Create HTML table row.
 *
 * \param text (str) HTML code to be placed inside the row.
 */
function tr(text) {
    return '<tr>' + text + '</tr>';
}

/**
 * Create HTML table cell element.
 *
 * \param text (str) The text to be placed inside the cell.
 */
function td(text) {
    return '<td>' + text + '</td>';
}

/**
 * Create HTML button with which a key-value pair is deleted from persistence.
 *
 * \param key (str) that'll be deleted when the created button is clicked.
 */
function deleteButton(key) {
    var format = '<button '
        + 'class="btn btn-default" '
        + 'data-key="{key}" '
        + 'onclick="deleteKey(\'{key}\')" '
        + '>Delete</button>'
    return format.replace(/{key}/g, key);
}

/**
 * Create HTML table row element.
 *
 * \param key (str) text into the key cell.
 * \param value (str) text into the value cell.
 */
function row(key, value) {
    return $(tr(td(key) + td(value) + td(deleteButton(key))));
}

/**
 * Clear and reload the values in data table.
 */
function refreshTable() {
    $.get('/values', function(data) {
        var attr,
            mainTable = $('#mainTable tbody');
        mainTable.empty();
        for (attr in data) {
            if (data.hasOwnProperty(attr)) {
                mainTable.append(row(attr, data[attr]));
            }
        }
    });
}

/**
 * Delete key-value pair.
 *
 * \param key (str) The key to be deleted.
 */
function deleteKey(key) {
    /*
     * Delete the key.
     * Reload keys and values in table to reflect the deleted ones.
     * Set keyboard focus to key input: ready to start typing.
     */
    $.post('/delete', {key: key}, function() {
        refreshTable();
        $('#keyInput').focus();
    });
}

$(document).ready(function() {
    var keyInput = $('#keyInput'),
        valueInput = $('#valueInput');

    refreshTable();
    $('#addForm').on('submit', function(event) {
        var data = {
            key: keyInput.val(),
            value: valueInput.val()
        };
        /*
         * Persist the new key-value pair.
         * Clear the inputs.
         * Set keyboard focus to key input: ready to start typing.
         */
        $.post('/add', data, function() {
            refreshTable();
            keyInput.val('');
            valueInput.val('');
            keyInput.focus();
        });
        /* Prevent HTTP form submit */
        event.preventDefault();
    });

    /* Focus keyboard input into key input; ready to start typing */
    keyInput.focus();
});
