address = 'http://localhost:8080/niths/';
avgTimeout = 5000;
roles = '';

$(document).ready(function() {

  $('#search-form').submit(function() {
    $.get(address + 'roles', function(data) {
      roles = data;
    });

    $.get(address + 'students/roles', function(data) {
      $.each(data, function(key, student) {
        displayStudent(student, roles);
      });
    });

    return false;
  });

  function displayStudent(student, allRoles) {
    var gen = generateRoleCheckboxes(student, allRoles);
    $('#students').append(
      '<li id="student-' + student.id + '">' +
        '<form action="#">' +
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
        '</form>' +
        '<div>' +
          '<button>Delete</button>' +
        '</div>' +
      '</li>'
    );
  }

  function generateRoleCheckboxes(student, allRoles) {
    var gen = '';
    $.each(allRoles, function(outerKey, outerRole) {
      var chb =
        '<div class="role">' +
          '<input type="checkbox" id="chb-' + student.id + '-' + outerRole.id +
              '" name="role" value="role-' + outerRole.id + '" ';
      $.each(student.roles, function(innerKey, innerRole) {
        if (outerRole.id == innerRole.id) {
          chb += 'checked="checked"';
        }
      });

      gen += chb + ' />' +
      '<label for="chb-' + student.id + '-' + outerRole.id + '">' +
          outerRole.roleName.toLowerCase().replace(/_/g, ' ') +
      '</label>';
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

$(document).on('click', 'input[type=checkbox]', function(event) {
  $.ajax({
    url:     address + 'students/' + /chb-(\d+)-\d+/.exec(event.target.id)[1] +
                 '/role/' + /chb-\d+-(\d+)/.exec(event.target.id)[1],
    type:    $(event.target).is(':checked') ? 'POST' : 'DELETE',
    success: function(data) {
      
    },
    error:   function(xhr) {
      alert(JSON.stringify(xhr));
    }
  });
});