-- tạo csdl Sufee
create database Sufee
go

use Sufee
go

--1 bảng quản trị viên lưu thông tin người quản trị
create table Admins (
	AdminId int primary key identity, -- khoá chính
	Email varchar(250) not null unique, -- email đăng nhập
	Passwords varchar(128) not null, -- mật khẩu đăng nhập
	Fullname nvarchar(250) not null, -- họ và tên người quản trị
	Birthday date not null default getdate(), -- ngày tháng năm sinh
	IdCard varchar(20) not null unique, -- chứng minh nhân dân
	Gender int default 1, -- giới tính
	AdminAddress ntext not null, -- địa chỉ
	Phone varchar(20) not null unique, -- số điện thoại
	Avatar ntext, -- ảnh đại diện
	Status int default 1 -- trạng thái
)
go

--2 bảng khách hàng
create table Customers (
	CustomerId int primary key identity, -- khoá chính
	Fullname nvarchar(250) not null, -- họ và tên khách hàng
	Email varchar(250) not null unique, -- email để đăng nhập
	Phone varchar(20) null unique, -- số điện thoại
	Passwords varchar(128) not null, -- mật khẩu
	Avatar ntext, -- ảnh đại diện
	Gender int default 1, -- giới tính
	Birthday date default getdate(), -- ngày sinh
	CustomerAddress ntext not null, -- địa chỉ
	CheckCode varchar(20), -- mã kiểm tra
	CustomerStatus int not null default 1 -- trạng thái
)
go

--3 bảng danh mục sản phẩm lưu thông tin danh mục sản phẩm
create table Categories (
	CategoryId int primary key identity, -- khoá chính
	CategoryName nvarchar(250) not null, -- tên danh mục
	CategoryPiority int not null, -- độ ưu tiên của danh mục
	ParentId int not null, -- khoá ngoại với Category (self)
	ParentName nvarchar(250) null, -- tên danh mục cha
	CategoryStatus int not null default 1 -- trạng thái
)
go

--4 bảng hãng sản xuất
create table Brands (
	BrandId int primary key identity, -- khoá chính
	BrandName nvarchar(250) not null, -- tên hãng
	BrandPiority int not null, -- độ ưu tiên của hãng
	BrandLogo ntext not null, -- logo của hãng
	BrandStatus int not null default 1 -- trạng thái
)
go

--5 bảng sản phẩm lưu thông tin tên sản phẩm, danh mục và hãng sản xuất
create table Products (
	ProductId int primary key identity, -- khoá chính
	ProductName nvarchar(250) not null, -- tên sản phẩm
	ProductCode varchar(50) not null, -- mã hiệu sản phẩm
	StarAvg float not null default 5, -- số sao trung bình của sản phẩm (sao được đánh giá) - mặc định là 5 sao, cộng trung bình với các đánh giá khác để ra cái này
	FeatureImage ntext not null, -- hình ảnh sản phẩm
	Images ntext, -- Nhiều ảnh
	Price float not null default 0, -- giá sản phẩm
	ProductSale int not null default 0, -- phần trăm hạ giá\
	Warranty int not null default 0, -- thời gian bảo hành (tính theo tháng)
	SaleQuantity int not null default 0, -- số sản phẩm đã bán
	ProductDescription nvarchar(250) not null default N'Đang cập nhật', -- mô tả sản phẩm
	SpecificationName ntext not null, -- tên thông số kỹ thuật (có thể trùng nhau do có thể khác danh mục sản phẩm)
	SpecificationValue ntext not null, --Mô tả chi tiết
	CategoryId int not null foreign key references Categories(CategoryId), -- khoá ngoại mã danh mục
	BrandId int not null foreign key references Brands(BrandId), -- khoá ngoại mã hãng sản xuất
	ProductStatus int not null default 1 -- trạng thái
)
go

-- bảng bình luận (đánh giá) sản phẩm (bắt buộc đăng nhập mới được đánh giá)
create table ProductComments (
	ProductCommentId int primary key identity, -- khoá chính
	ProductId int foreign key references Products(ProductId), -- khoá ngoại mã sản phẩm được đánh giá
	CustomerId int foreign key references Customers(CustomerId), -- khoá ngoại mã khách hàng đánh giá
	CommentRate int not null default 5, -- đánh giá (số sao)
	CommentContent nvarchar(500) null, -- bình luận (tối đa 500 kí tự)
	CommentTime datetime not null default getdate(), -- ngày giờ đánh giá
	CommentStatus int not null default 1 -- trạng thái
)
go

-- bảng các danh sách yêu thích
create table Wishlists (
	WishlistId int primary key identity, -- khoá chính
	CustomerId int foreign key references Customers(CustomerId), -- khoá ngoại mã khách hàng
	ProductId int not null foreign key references Products(ProductId), -- khoá ngoại mã sản phẩm được yêu thích
	WishlistStatus int not null default 1 -- trạng thái
)
go

-- bảng danh mục tin tức
create table Catalogs (
	CatalogId int not null primary key identity, -- khoá chính
	CatalogName nvarchar(250) not null unique, -- tên danh mục tin tức
	Piority int not null, -- độ ưu tiên
	ParentId int not null default 0, -- danh mục cha (self)
	CatalogStatus int not null default 1 -- trạng thái
)
go

-- bảng tin tức
create table News (
	NewsId int primary key identity, -- khoá chính
	CatalogId int foreign key references Catalogs(CatalogId), -- khoá ngoại mã danh mục tin tức
	AdminId int not null foreign key references Admins(AdminId), -- khoá ngoại quản trị viên thêm hoặc sửa bản ghi
	NewTitle nvarchar(250) not null, -- tiêu đề tin tức
	NewImage ntext not null, -- hình ảnh đại diện cho tin tức
	NewDescription nvarchar(250) not null, -- mô tả tin tức
	NewContent ntext not null, -- nội dung tin tức
	CountView int,
	Createdate datetime default getdate(),
	NewStatus int not null default 1 -- trạng thái
)
go

-- bảng banner
create table Banners (
	BannerId int primary key identity, -- khoá chính
	BannerPiority int not null, -- độ ưu tiên của banner
	BannerImage ntext not null, -- hình ảnh banner
	BannerDescription nvarchar(250) not null, -- mô tả banner (xuất hiện trong backend)
	BannerStatus int not null default 1
)
go

-- bảng đơn hàng  -- 2312awe
create table Orders (
	OrderId int primary key identity, -- khoá chính
	CustomerId int not null foreign key references Customers(CustomerId), -- khoá ngoại mã người đặt hàng
	FullName nvarchar(250) not null,
	Email nvarchar(50) not null,
	Phone nvarchar(20) not null,
	OrderTotalAmount float not null default 0, -- tổng tiền thanh toán
	OrderNote ntext null, -- ghi chú đơn hàng
	OrderAddress ntext null, -- địa chỉ nhận hàng
	OrderStatus int not null default 1 -- trạng thái đơn hàng
)
go

-- bảng chi tiết đơn hàng
create table OrderDetails (
	primary key(OrderId, ProductId), --2 khoá ngại -> 1 khoá chính
	OrderId int foreign key references Orders(OrderId), -- khoá ngoại mã đơn hàng
	ProductId int foreign key references Products(ProductId), -- khoá ngoại mã sản phẩm (1 trong 2 khoá ngoại này)
	OrderDetailQuantity int not null default 1, -- số lượng sản phẩm
	OrderDetailPrice float not null default 0, -- giá sản phẩm tại thời điểm đặt hàng
	OrderDetailStatus int not null default 1 -- trạng thái
)
go

-- bảng logo
create table Logo (
	LogoId int not null primary key identity, -- khoá chính
	LogoImage varchar(250) not null, -- hình ảnh logo
	LogoStatus int not null default 1 -- trạng thái
)
go

-- bảng icon
create table Icons (
	IconId int not null primary key identity, -- khoá chính
	IconImage varchar(250) not null unique, -- hình ảnh icon
	IconStatus int not null default 1 -- trạng thái
)
go

-- bảng liên hệ
create table Contacts (
	ContactId int primary key identity, -- khoá chính
	ContactAddress ntext not null, -- địa chỉ liên hệ
	ContactEmail varchar(250) not null unique, -- email liên hệ
	ContactHotline varchar(20) not null unique, -- số điện thoại đường dây nóng
	ContactStatus int not null default 1
)
go

-- bảng FAQ
create table FAQs (
	FAQId int primary key identity, -- khoá chính
	FastQuestion nvarchar(250) not null unique, -- câu hỏi thường gặp
	FastAnswer ntext not null, -- câu trả lời
	FAQStatus int not null default 1
)
go

-- bảng danh mục phản hồi
create table FeedbackCatalogs (
	FeedbackCatalogId int not null primary key identity, -- khoá chính
	FeedbackCatalogName nvarchar(250), -- tên danh mục phản hồi
	FeedbackCatalogStatus int not null default 1 -- trạng thái
)
go

-- bảng phản hồi
create table Feedbacks (
	FeedbackId int not null primary key identity, -- khoá chính
	FeedbackCatalogId int not null foreign key references FeedbackCatalogs(FeedbackCatalogId), -- khoá ngoại danh mục phản hồi
	Fullname nvarchar(250) null, -- họ tên người phản hồi
	Phone varchar(20) null, -- số điện thoại người phản hồi
	Email varchar(250) null, -- email người phản hồi
	FeedbackAddress ntext null, -- địa chỉ người phản hồi
	Content ntext not null, -- nội dung phản hồi
	FeedbacksTime datetime not null default getdate(), -- thời gian phản hồi
	AdminId int null foreign key references Admins(AdminId), -- khoá ngoại quản trị viên xem phản hồi
	FeedbackStatus int not null default 1 -- trạng thái
)
go

-- bảng giới thiệu
create table Introductions (
	IntroductionId int not null primary key identity, -- khoá chính
	IntroductionContent ntext not null, -- nội dung giới thiệu
	IntroductionStatus int not null default 1 -- trạng thái
)
go