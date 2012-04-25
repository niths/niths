address = 'http://10.110.58.108:8080/niths/';
avgTimeout = 5000;

$(document).ready(function() {

  $('#search-form').submit(function() {
    $.ajax({
      url:     address + 'students',
      type:    'GET',
      timeout: avgTimeout,
      success: function(data) {
        alert(JSON.stringify(data));
        $.each(data, function(key, student) {
          displayStudent(student);
        });
      },
      error:   function(xhr) {
        alert(JSON.stringify(xhr));
      }
    });

    return false;
  });

  function displayStudent(student) {
    $('#students').append(
      '<li id="student-' + student.id + '">' +
        '<div>' +
          '<span class="name">' +
            student.firstName + ' ' + student.lastName + ' ' +
          '</span>' +
          '<a href="mailto:' + student.email + '">' +
            student.email +
          '</a>' +
        '</div>' +
        '<div>' +
          '<button>Delete</button>' +
        '</div>' +
      '</li>'
    );
  }
});

$(document).on('click', 'button', function(event) {
  var currentListElementId = $(event.target).parent().parent().attr('id');

  $.ajax({
    url:     address + 'students/' + /student-(\d+)/g.exec(
                 $(event.target).parent().parent().attr('id'))[1],
    type:    'DELETE',
    timeout: avgTimeout,
    success: function(data) {
      $('#' + currentListElementId).fadeOut('slow', function() {});
    },
    error:   function(xhr) {
      alert(JSON.stringify(xhr));
    }
  });
});