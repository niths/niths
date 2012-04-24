address = 'http://146.247.156.31:8080/niths/';

$(document).ready(function() {

  $('#search-form').submit(function() {
    $.ajax({
      url:     address + 'students',
      type:    'GET',
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

$(document).on('click', 'button', function() { alert("foo"); });