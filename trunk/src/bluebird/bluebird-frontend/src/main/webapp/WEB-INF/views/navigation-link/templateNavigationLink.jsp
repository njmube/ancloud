<script type="text/x-handlebars-template" id="navigationLinkTemplate">
	<tr class="ar-item ar-dataItem" data-ar-groupId="{{groupId}}">
		<td class="ar-groupIndex">
		</td>
		<td>
			<input type="hidden" name="navigationLinks[0].id" />
			<input type="hidden" name="navigationLinks[0].groupId" class="ar-groupId"/>
			<input type="hidden" name="navigationLinks[0].groupIndex" class="ar-groupIndex"/>
			<input type="hidden" name="navigationLinks[0].itemIndex" class="ar-itemIndex"/>
			<div class="input-group">
				<span class="input-group-btn">
					<button class="btn btn-default bb-fa-btn fa-angle-double-down bb-navigationLink-addLinkButton" type="button"></button>
				</span>
				<bb-ex:autocomplete name="navigationLinks[0].messageCode" 
									cssClass="form-control" 
									sourcePath="/message/ajaxGetAllMessage" 
									queryStringProperty="message" 
									displayProperties="message,key,language"
									submitProperty="key"/>
				<span class="input-group-btn">
					<button class="btn btn-default bb-fa-btn fa-angle-double-right bb-navigationLink-addNestedLinkButton" type="button"></button>
				</span>
			</div>
		</td>
		<td>
			<input type="text" name="navigationLinks[0].path" class="form-control"/>
		</td>
		<td>
			<button class="btn btn-default bb-fa-btn fa-minus-square bb-navigationLink-removeLinkButton" type="button"></button>
		</td>
	</tr>
</script>
