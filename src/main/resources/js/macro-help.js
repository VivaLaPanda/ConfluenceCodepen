// The pattern below is a 'module' pattern based upon iife (immediately invoked function expressions) closures.
// see: http://benalman.com/news/2010/11/immediately-invoked-function-expression/ for a nice discussion of the pattern
// The value of this pattern is to help us keep our variables to ourselves.
//
macroHelpDocumentation = (function( $ ){

   // module variables:
   var methods = new Object();
   var initialAuiBlanketZIndex = "";
   var embeddedHelpMacroId     = "my-embedded-macro-help";
   var macroBrowserId          = "macro-browser-dialog";

   // module methods
   methods[ 'getMacroHelp' ] = function( e, pluginId, restVersion, macroId ){
      // Prevent any attached event handlers from executing as that can result
      // in opening unwanted windows or closing the macro browser
      e.preventDefault();
      e.stopPropagation();
      $( e.target ).unbind();

      // Use ajax to retrieve the documentation from the plugin's rest service, append
      // the returned html to the end of the document and show the dialog.
      // Note, the z index of the aui-blanket (the semi-transparent gray layer) needs
      // to be moved up so it's now in front of the macro browser but behind the
      // documentation dialog.
      var helpUrl = AJS.Data.get( "base-url" ) + "/rest/"+pluginId+"/"+restVersion+"/help/" + macroId;
      AJS.$.ajax({
         url: helpUrl,
         type: "GET",
         dataType: "json",
      }).done( function ( data ) {
         //alert( data[ "message-body" ] );
         var macroBrowserZIndex;

         // append the returned html to the body of the page
         AJS.$( "body" ).append( data[ "message-body" ] );

         // give the last section an id
         AJS.$( "section" ).last().attr( "id", embeddedHelpMacroId );

         // show the help dialog
         AJS.dialog2( "#"+embeddedHelpMacroId ).show();

         // move the dialog on top of the macro browser dialog
         macroBrowserZIndex = AJS.$( "#"+macroBrowserId ).zIndex();
         AJS.$( "#"+embeddedHelpMacroId ).zIndex( macroBrowserZIndex + 500 );

         // move the aui-blanket between the macro browser and the help dialog
         initialAuiBlanketZIndex = AJS.$( ".aui-blanket" ).zIndex();
         AJS.$( ".aui-blanket" ).zIndex( macroBrowserZIndex + 250 );

      }).fail( function (self, status, error ){ alert( error );
      });
   }

   methods[ 'closeMacroHelp' ] = function(){
      AJS.dialog2("#"+embeddedHelpMacroId).remove();
      // put the aui-blanket back
      AJS.$( ".aui-blanket" ).attr( 'style', 'z-index: '+initialAuiBlanketZIndex );
      AJS.$( ".aui-blanket" ).attr( 'aria-hidden', 'false' );
   }

   // return the object with the methods
   return methods;

// End closure
})( AJS.$ || jQuery );