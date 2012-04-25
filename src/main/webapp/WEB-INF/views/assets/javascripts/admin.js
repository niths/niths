address = 'http://10.110.58.108:8080/niths/';
avgTimeout = 5000;

$(document).ready(function() {

  $('#search-form').submit(function() {
    var roles = {};

    $.get(address + 'roles', function(data) {
      roles = data;
    });

    $.get(address + 'students/roles', function(data) {
      $.each(data, function(key, student) {
        displayStudent(student, roles);
      });
    });
    /*
    $.ajax({
      url:     address + 'students/roles',
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
    */

    return false;
  });

  function displayStudent(student, allRoles) {
    var gen = generateRoleCheckboxes(student.roles, allRoles);
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
          gen +
        '</div>' +
        '<div>' +
          '<button>Delete</button>' +
        '</div>' +
      '</li>'
    );
  }

  function generateRoleCheckboxes(studentRoles, allRoles) {
    var gen = '';
    $.each(allRoles, function(outerKey, outerRole) {
      var chb =
        '<div class="role">' +
          '<input type="checkbox" name="role" value=role-"' +
              outerRole.id + '" ';
      $.each(studentRoles, function(innerKey, innerRole) {
        if (outerRole.id == innerRole.id) {
          chb += 'checked="checked"';
        }
      });

      gen += chb + ' />';
      gen += outerRole.roleName.toLowerCase().replace(/_/g, ' ');
      gen += '</div>';
    });

    return gen;
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