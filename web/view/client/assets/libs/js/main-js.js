function validateId(id, url) {
    var getParam = id;
    var getUrlParameter = function getUrlParameter(sParam) {
        var sPageURL = window.location.search.substring(1),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;

        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split('=');

            if (sParameterName[0] === sParam) {
                return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
            }
        }
    };

    function isInt(data) {
        regExpTest = /^[-+]?\d+$/;
        if (regExpTest.test(data)) {
            console.log(data);
            return true;
        } else {
            return false;
        }
    }

    var id = getUrlParameter(getParam);
    console.log(id);

    if (!isInt(id)) {
        Swal({
            type: 'error',
            title: 'Có lỗi xảy ra...',
            text: 'Mã không hợp lệ',
        }).then(function () {
            document.location = url;
        });
    }
}

$(document).ready(function ($) {

    $("#send-email-custom").click(function (event) {
        event.preventDefault();

        var checkValues = $('input[name=CustomerId]:checked').map(function () {
            return $(this).val();
        }).get();

        listEmail = (checkValues.length > 0) ? checkValues : ["nothing"];

        console.log(listEmail);
        document.location = "/Admin/BackendCustomer/SendEmail/?ListEmail=" + listEmail;
    });

    $("#searchProduct").keyup(function (e) {
        if (e.keyCode == 13) {
            $("#search-form").submit();
        }
    });

    $("#btn-upload").click(function (event) {
        event.preventDefault();
        var finder = new CKFinder();
        finder.selectActionFunction = function (url) {
            $("#image-url").val(url);
        }
        finder.popup();
    });

    $("#btn-uploads").click(function (event) {
        event.preventDefault();
        var finder = new CKFinder();
        finder.selectActionFunction = function (url, file, files) {
            var str = "";
            $.each(files, function (key, val) {
                str += val.url + ";";
            });

            $("#images-url").val(str);
        }
        finder.popup();
    });

    'use strict';

    if ($(".notification-list").length) {

        $('.notification-list').slimScroll({
            height: '250px'
        });

    }

    if ($(".menu-list").length) {
        $('.menu-list').slimScroll({

        });
    }

    if ($(".sidebar-nav-fixed a").length) {
        $('.sidebar-nav-fixed a')

        .click(function(event) {

            if (
                location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') &&
                location.hostname == this.hostname
                ) {

                var target = $(this.hash);
            target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');

            if (target.length) {
                event.preventDefault();
                $('html, body').animate({
                    scrollTop: target.offset().top - 90
                }, 1000, function() {
                    var $target = $(target);
                    $target.focus();
                    if ($target.is(":focus")) {
                        return false;
                    } else {
                        $target.attr('tabindex', '-1');
                        $target.focus();
                    };
                });
            }
        };
        $('.sidebar-nav-fixed a').each(function() {
            $(this).removeClass('active');
        })
        $(this).addClass('active');
    });

    }

    if ($('[data-toggle="tooltip"]').length) {

        $('[data-toggle="tooltip"]').tooltip()

    }
    if ($('[data-toggle="popover"]').length) {
        $('[data-toggle="popover"]').popover()

    }

    if ($('.chat-list').length) {
        $('.chat-list').slimScroll({
            color: 'false',
            width: '100%'
        });
    }
});

var i = 2;

function removeContent(i) {
    var m = $("#content-" + i);
    m.remove();
}

$("#add-specification").click(function(event) {
    event.preventDefault();

    var strHtml = '<div class="form-group" id="content-' + i + '">';
    strHtml += '<label class="col-from-label">Thông số kỹ thuật ' + i + ':</label>';
    strHtml += '<div class="row">';
    strHtml += '<div class="col-md-7">';
    strHtml += '<input name="ProductSpecification' + i + '" type="text" class="form-control" placeholder="Tên thông số kỹ thuật ' + i + '...">';
    strHtml += '</div>';
    strHtml += '<div class="col-md-3">';
    strHtml += '<input name="ProductDetailValue' + i + '" type="text" class="form-control" placeholder="Giá trị ' + i + '...">';
    strHtml += '</div>';
    strHtml += '<div class="col-md-2">';
    strHtml += '<a class="btn btn-sm btn-danger" id="remove-' + i + '" onclick="return removeContent(' + i + ');" title="Xoá" style="color: #FFF;"><i class="fas fa-window-close"></i></a>';
    strHtml += '</div>';
    strHtml += '</div>';
    strHtml += '</div>';

    $("#append-specification").append(strHtml);
    i++;
});

$("#add-product-combo").click(function(event) {
    event.preventDefault();

    var strHtml2 = '<div class="form-group" id="content-' + i + '">';
    strHtml2 += '<label class="col-from-label">Sản phẩm 1:</label>';
    strHtml2 += '<div class="row">';
    strHtml2 += '<div class="col-md-7">';
    strHtml2 += '<input list="ProductId1"type="text" class="form-control" placeholder="Tên sản phẩm 1...">';
    strHtml2 += '<datalist id="ProductId1">';
    strHtml2 += '<option value="Internet Explorer">Sản phẩm 1</option>';
    strHtml2 += '<option value="Internet Explorer">Sản phẩm 1</option>';
    strHtml2 += '<option value="Internet Explorer">Sản phẩm 1</option>';
    strHtml2 += '<option value="Internet Explorer">Sản phẩm 1</option>';
    strHtml2 += '<option value="Internet Explorer">Sản phẩm 1</option>';
    strHtml2 += '</datalist>';
    strHtml2 += '</div>';
    strHtml2 += '<div class="col-md-3">';
    strHtml2 += '<input id="ProductDetailValue1" name="ProductDetailValue1" type="text" class="form-control" placeholder="Số lượng...">';
    strHtml2 += '</div>';
    strHtml2 += '<div class="col-md-2">';
    strHtml += '<a class="btn btn-sm btn-danger" id="remove-' + i + '" onclick="return removeContent(' + i + ');" title="Xoá" style="color: #FFF;"><i class="fas fa-window-close"></i></a>';
    strHtml2 += '</div>';
    strHtml2 += '</div>';
    strHtml2 += '</div>';

    $("#append-product-combo").append(strHtml2);
    i++;
});
$(document).ready(function () {
    var navListItems = $('div.setup-panel div a'),
        allWells = $('.setup-content'),
        allNextBtn = $('.nextBtn');
    allPrevBtn = $('.prevBtn');

    allWells.hide();

    navListItems.click(function (e) {
        e.preventDefault();
        var $target = $($(this).attr('href')),
            $item = $(this);

        if (!$item.hasClass('disabled')) {
            navListItems.removeClass('btn-primary').addClass('btn-default');
            $item.addClass('btn-primary');
            allWells.hide();
            $target.show();
            $target.find('input:eq(0)').focus();
        }
    });

    allNextBtn.click(function () {
        var curStep = $(this).closest(".setup-content"),
            curStepBtn = curStep.attr("id"),
            nextStepWizard = $('div.setup-panel div a[href="#' + curStepBtn + '"]').parent().next().children("a"),
            curInputs = curStep.find("input[type='text'],input[type='url']"),
            isValid = true;

        $(".form-group").removeClass("has-error");

        for (var i = 0; i < curInputs.length; i++) {
            if (!curInputs[i].validity.valid) {
                isValid = false;
                $(curInputs[i]).closest(".form-group").addClass("has-error");
            }
        }

        if (isValid) {
            nextStepWizard.removeAttr('disabled').trigger('click');
        }
    });

    allPrevBtn.click(function () {
        var curStep = $(this).closest(".setup-content"),
            curStepBtn = curStep.attr("id"),
            prevStepWizard = $('div.setup-panel div a[href="#' + curStepBtn + '"]').parent().prev().children("a")
        curInputs = curStep.find("input[type='text'],input[type='url']"),
            isValid = true;

        $(".form-group").removeClass("has-error");

        for (var i = 0; i < curInputs.length; i++) {
            if (!curInputs[i].validity.valid) {
                isValid = false;
                $(curInputs[i]).closest(".form-group").addClass("has-error");
            }
        }

        if (isValid) {
            prevStepWizard.removeAttr('disabled').trigger('click');
        }
    });

    $('div.setup-panel div a.btn-primary').trigger('click');
});