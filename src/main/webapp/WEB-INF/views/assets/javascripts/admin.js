address = 'http://ec2-46-137-46-84.eu-west-1.compute.amazonaws.com:8080/niths/';
avgTimeout = 5000;
roles = '';

$(document).ready(function() {

  $('#search-form').submit(function() {

    // Empty the list every time a new search is issued
    $('#students').empty();

    // Get all roles
    $.get(address + 'roles', function(data) {
      roles = data;
    });

    // Do a simple search
    $.get(address + 'students/search?' + $(this).serialize(), function(data) {
      var allStudentsContent = '';
      $.each(data, function(key, student) {
        allStudentsContent += displayStudent(student, roles);
      });

      // Perform animation
      $('#students').hide();
      $('#students').append(allStudentsContent).fadeIn('slow');
    });

    // Prevent the form from executing the default action
    return false;
  });

  function displayStudent(student, allRoles) {
    var studentContent = '';
    var generatedCheckboxes = generateRoleCheckboxes(student, allRoles);
    studentContent +=
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
            generatedCheckboxes +
          '</div>' +
        '</form>' +
        '<div>' +
          '<button>Delete</button>' +
        '</div>' +
      '</li>';

    return studentContent;
  }

  // Displays checkboxes for all roles available, if the student already has the
  // role, the checkbox will appear as checked
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
        outerRole.roleName.toLowerCase()
            .replace(/_/g, ' ').replace(/role/, '') +
      '</label>';
      gen += '</div>';
    });

    return gen;
  }
});

// Delete a student
$(document).on('click', 'button', function(event) {
  var currentListElementId = $(event.target).parent().parent().attr('id');

  $.ajax({
             // Forge the URL with the extracted student's id
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

// Update a student's roles
$(document).on('click', 'input[type=checkbox]', function(event) {
  $.ajax({

             // Extracting the student id and the role id to be added / removed
    url:     address + 'students/' + /chb-(\d+)-\d+/.exec(event.target.id)[1] +
                 '/role/' + /chb-\d+-(\d+)/.exec(event.target.id)[1],
    type:    $(event.target).is(':checked') ? 'POST' : 'DELETE',
    timeout: avgTimeout,
    success: function(data) {
      
    },
    error:   function(xhr) {
      alert(JSON.stringify(xhr));
    }
  });
});