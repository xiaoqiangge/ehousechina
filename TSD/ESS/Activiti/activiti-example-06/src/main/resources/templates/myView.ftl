<table border="1">
		<tr>
			<td>流程定义Id</td>
			<td>部署Id</td>
			<td>流程定义名称</td>
			<td>流程定义Key</td>
			<td>版本号</td>
			<td>XML资源名称</td>
			<td>图片资源名称</td>
			<td>操作</td>
		</tr>
	<#list list as l>
		<tr>
			<td>${l.id}</td>
			<td>${l.deploymentId}</td>
			<td>${l.name}</td>
			<td>${l.key}</td>
			<td>${l.version}</td>
			<td><a href="/read?pdid=${l.id}&resourceName=${l.resourceName}" target="_blank">${l.resourceName}</a></td>
			<td><a href="/read?pdid=${l.id}&resourceName=${l.diagramResourceName}" target="_blank">${l.diagramResourceName}</a></td>
			<td><a href="/start?pdid=${l.id}">启动</a></td>
		</tr>
	</#list>
</table>