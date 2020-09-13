/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author ASUS
 */
public class Paging {
    // trang hiện tại
    private int currentPage;

    // tổng số bản ghi
    private int totalRecords;

    // số bản ghi trên 1 trang
    private int limitRecords;

    // bản ghi bắt đầu
    public int startRecord;

    // tổng số bản ghi
    private int totalPages;

    // link hiện tại
    private String currentLink;

    // link của trang đầu tiên
    private String firstLink;

    // số lượng nút phân trang sẽ hiển thị
    private int rangeSize;

    // trang nhỏ nhất trên thanh phân trang
    private int minPage;

    // trang lớn nhất trên thanh phân trang
    private int maxPage;

    public Paging() {
    }

    public Paging(int currentPage, int totalRecords, int limitRecords, String currentLink, String firstLink) {
        this.currentPage = currentPage;
        this.totalRecords = totalRecords;
        this.limitRecords = limitRecords;
        this.currentLink = currentLink;
        this.firstLink = firstLink;
        rangeSize = 5;

        init();
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getLimitRecords() {
        return limitRecords;
    }

    public void setLimitRecords(int limitRecords) {
        this.limitRecords = limitRecords;
    }

    public int getStartRecord() {
        return startRecord;
    }

    public void setStartRecord(int startRecord) {
        this.startRecord = startRecord;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getCurrentLink() {
        return currentLink;
    }

    public void setCurrentLink(String currentLink) {
        this.currentLink = currentLink;
    }

    public String getFirstLink() {
        return firstLink;
    }

    public void setFirstLink(String firstLink) {
        this.firstLink = firstLink;
    }

    public int getRangeSize() {
        return rangeSize;
    }

    public void setRangeSize(int rangeSize) {
        this.rangeSize = rangeSize;
    }

    public int getMinPage() {
        return minPage;
    }

    public void setMinPage(int minPage) {
        this.minPage = minPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }
    
        // hàm gán các giá trị cho các thuộc tính của class
    private void init() {
        // số bản ghi trên 1 trang luôn phải lớn hơn 0, nếu nhỏ hơn 0 thì gán luôn = 0
        if (limitRecords < 0) {
            limitRecords = 0;
        }

        // tổng số trang - dùng hàm làm tròn trong mvc báo lỗi nên chuyển về cách thủ công này
        totalPages = (totalRecords % limitRecords == 0) ? (totalRecords / limitRecords) : (totalRecords / limitRecords + 1);

        // trang hiện tại luôn lớn hơn 1 (vì tổng số trang bao giờ cũng >= 1) nếu nhỏ hơn thì gán = 1
        if (currentPage < 1) {
            currentPage = 1;
        }

        // trang hiện tại luôn bé hơn tổng số trang, nếu lớn hơn thì gán bằng tổng số trang
        if (currentPage > totalPages) {
            currentPage = totalPages;
        }

        // bản ghi bắt đầu lấy
        startRecord = (currentPage - 1) * limitRecords;

        // tính điểm giữa của thanh phân trang
        int middle = (rangeSize % 2 == 0) ? (rangeSize / 2) : (rangeSize / 2 + 1);

        // gán các giá trị cho nút lớn và bé nhất trên thanh phân trang
        // nếu tổng số trang bé hơn giới hạn số nút trên thanh phân trang
        if (totalPages < rangeSize) {
            // gán trang nhỏ nhất = 1
            minPage = 1;
            // gán trang lớn nhất = tổng số trang
            maxPage = totalPages;
        } else {
            // nếu tổng số trang lớn hơn giới hạn số nút trên thanh phân trang
            // gán trang nhỏ nhất
            minPage = currentPage - middle + 1;
            // gán trang lớn nhất
            maxPage = currentPage + middle - 1;

            // fix giá trị cho 2 nút
            // nếu nút bé nhất < 1
            if (minPage < 1) {
                // gán nút bé nhất = 1
                minPage = 1;
                // gán nút lớn nhất bằng giới hạn số nút trên thanh phân trang
                maxPage = rangeSize;
            } else if (maxPage > totalPages) {
                // nếu nút lớn nhất > tổng số trang
                // gán nút lớn nhất = tổng số trang
                maxPage = totalPages;
                // gán giá trị cho nút nhỏ nhất
                minPage = totalPages - rangeSize + 1;
            }
        }
    }
    
    // hàm sinh link
    // tham số truyền vào là trang (để sinh link cho các nút ở trên thanh phân trang)
    private String generateLink(int page) {
        if (page <= 1 && firstLink != null) {
            return firstLink;
        } else {
            // thay thế chuỗi "{p}" thành "?page=" trong thuộc tính CurrentLink. Tức là lúc truyền link hiện tại ở controller phải thêm chuỗi "{p}" vào phía sau cùng
            return currentLink.replace("{p}", "?page=" + page);
        }
    }
    // hàm sinh toàn bộ thanh phân trang (code html)
    // code html ở đây được tuỳ biến cho giao diện sử dụng bootstrap 4
    // khi sử dụng chỉ cần gán thẳng cho ViewBag là được
    public String generateHtml() {
        // khai báo 1 chuỗi là nội dung code html sẽ hiển thị cả cụm thanh phân trang
        String htmlGenerate = "";

        // chỉ khi tổng số trang lớn hơn giới hạn số bản ghi trên 1 trang mới hiển thị phân trang
        if (totalRecords > limitRecords) {
            htmlGenerate += "<nav aria-label='Page navigation example'>";
            htmlGenerate += "<ul class='pagination'>";

            if (currentPage > 1) {
                htmlGenerate += "<li class='page-item'><a class='page-link' href='" + generateLink(1) + "'>&laquo;&laquo;</a></li>";
                htmlGenerate += "<li class='page-item'><a class='page-link' href='" + generateLink(currentPage - 1) + "'>&laquo;</a></li>";
            } else {
                htmlGenerate += "<li class='disabled page-item'><a class='page-link'>&laquo;&laquo;</a></li>";
                htmlGenerate += "<li class='disabled page-item'><a class='page-link'>&laquo;</a></li>";
            }

            for (int i = minPage; i <= maxPage; i++) {
                if (currentPage == i) {
                    htmlGenerate += "<li class='active page-item'><span class='page-link'>" + i + "</span></li>";
                } else {
                    htmlGenerate += "<li class='page-item'><a class='page-link' href='" + generateLink(i) + "'>" + i + "</a></li>";
                }
            }

            if (currentPage < totalPages) {
                htmlGenerate += "<li class='page-item'><a class='page-link' href='" + generateLink(currentPage + 1) + "'>&raquo;</a></li>";
                htmlGenerate += "<li class='page-item'><a class='page-link' href='" + generateLink(totalPages) + "'>&raquo;&raquo;</a></li>";
            } else {
                htmlGenerate += "<li class='disabled page-item'><a class='page-link'>&raquo;</a></li>";
                htmlGenerate += "<li class='disabled page-item'><a class='page-link'>&raquo;&raquo;</a></li>";
            }

            htmlGenerate += "</ul>";
        }

        return htmlGenerate;
    }
    
    public String generateHtmlFrontend() {
        // khai báo 1 chuỗi là nội dung code html sẽ hiển thị cả cụm thanh phân trang
        String htmlGenerate = "";

        // chỉ khi tổng số trang lớn hơn giới hạn số bản ghi trên 1 trang mới hiển thị phân trang
        if (totalRecords > limitRecords) {
            htmlGenerate += "<ul class='store-pages'>";

            if (currentPage > 1) {
                htmlGenerate += "<li><a href='" + generateLink(currentPage - 1) + "'><i class='fa fa-caret-left'></i></a></li>";
            } else {
                htmlGenerate += "<li><a class='disabled'><i class='fa fa-caret-left'></i></a></li>";
            }

            for (int i = minPage; i <= maxPage; i++) {
                if (currentPage == i) {
                    htmlGenerate += "<li class='active'>" + i + "</li>";
                } else {
                    htmlGenerate += "<li><a href='" + generateLink(i) + "'>" + i + "</a></li>";
                }
            }

            if (currentPage < totalPages) {
                htmlGenerate += "<li><a href='" + generateLink(currentPage + 1) + "'><i class='fa fa-caret-right'></i></a></li>";
            } else {
                htmlGenerate += "<li><a class='disabled'><i class='fa fa-caret-right'></i></a></a></li>";
            }

            htmlGenerate += "</ul>";
        }

        return htmlGenerate;
    }
    
    
}
