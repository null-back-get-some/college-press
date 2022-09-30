/**
 * 
 */
  var cheditor;
  ClassicEditor.create(document.querySelector("#contents"), {
	ckfinder: {
			uploadUrl : '/image/upload'
		},
    // 언어
    language: "ko",
    // 폰트 종류
    fontFamily: {
      options: [
        'default',
        '궁서체',
      ]
    },
    // 폰트 크기
    fontSize: {
      options: [
        14,
        'default',
        16
      ]
    },
    // 폰트 색상
    fontColor: {
      // 한줄에 보여 줄 개수
      columns: 2,
      colors: [
        {
          color: 'hsl(0, 0%, 0%)'
        },
        {
          color: 'hsl(0, 0%, 60%)'
        }
      ]
    },
    // 폰트 배경 색상
    fontBackgroundColor: {
      columns: 2,
      colors: [
    	{
          color: 'hsl(0, 0%, 0%)'
        },
        {
          color: 'hsl(0, 0%, 60%)'
        }
      ]
    },
    // 커스텀 플러그인 클래스 추가
   // extraPlugins: [CustomUploadPlugin],
    // 미디어 기능
    mediaEmbed: {
      previewsInData: true
    }, 
    // Source Editor 속성 허용 범위
    htmlSupport: {
      allow: [
        {
          name: /.*/,
          attributes: true,
          classes: true,
          styles: true
        }
      ]
    }
  })
  
 // function CustomUploadPlugin(editor){
//    editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
 //     return new UploadAdapter(loader, 'test');
 //   }
//  }
  
  // 값 얻기
 // var content = ckeditor.getData();
  // focus
//  ckeditor.focus();
  
