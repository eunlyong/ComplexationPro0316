<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
	<%@ page import="dao.*" import="model.*" import="servlet.*"
	import="java.io.*" import="java.net.*" import="java.sql.SQLException"
	import="org.apache.jena.atlas.lib.Sync"
	import="com.hp.hpl.jena.query.*" import="com.hp.hpl.jena.rdf.model.*"
	import="com.hp.hpl.jena.shared.Lock" import="java.util.*"
	import="com.hp.hpl.jena.sparql.mgt.SystemInfo"
	import="com.hp.hpl.jena.tdb.*" import="com.hp.hpl.jena.update.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	response.setCharacterEncoding("utf-8");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript" charset="utf-8"
	src="../../eunlyong_js/function.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
    var marker = 0;
	function insertNewBasetype() {
		if(marker==0)
		{
			var basetype1 = "<tr><td>basetype name:</td><td><input type='text' id='basetype_name' />"
			 + "</td><td></td><td></td></tr><tr><td>anomer:</td><td><span><select id='has_anomer' style='width: 165px'><option selected "
			+ "value='glycan:anomer_unknown'>Unknown</option><option value='glycan:anomer_none'>None Anomer</option>"
				+ "<option value='glycan:anomer_beta'>Anomer Beta</option><option value='glycan:anomer_alpha'>Anomer Alpha</option>"
				+ "</select> </span></td>"
				+ "<td>MSDB basetype id:</td><td><input type='text' id='has_basetype_id' /></td>"
				+ "</tr><tr><td>ring start:</td><td><input type='text' id='has_ring_start' /></td>"
				+ "<td>ring end:</td><td><input type='text' id='has_ring_end' /></td></tr><tr>"
				+ "<td>ring size:</td><td><input type='text' id='has_size' /></td><td>ring type:</td>"
				+ "<td><span><select id='has_ring_type' style='width: 165px'><option selected "
			+ "value='glycan:ring_type_unknown'>Unknown</option><option value='glycan:pyranose'>Pyranose</option>"
				+ "<option value='glycan:open'>Open</option><option value='glycan:furanose'>Furanose</option>"
				+ "</select> </span></td></tr>";
		    var basetype2 = "<tr><td colspan='4'>Configuration Information:</td></tr>"
				+ "<tr><td>absolute configuration:</td><td><span><select id='has_absolute_configuration'"
				+ " style='width: 165px'><option selected value='absolute_configuration_unknown'>Unknown</option>"
				+ "	<option value='absolute_configuration_laevus'>Laevus</option><option value='absolute_configuration_dexter'>"
				+ "Dexter</option></select> </span></td><td>relative configuration:</td><td><span>"
				+ "<select id='has_relative_configuration' style='width: 165px'><option selected value=''>SELECT</option>"
				+ "	<option value='glycan:manno'>Manno</option><option value='glycan:allo'>Allo</option>"
				+ "	<option value='glycan:talo'>Talo</option><option value='glycan:lyxo'>Lyxo</option>"
				+ "	<option value='glycan:erythro'>Erythro</option><option value='glycan:gluco'>Gluco</option>"
				+ "	<option value='glycan:arbino'>Arbino</option><option value='glycan:altro'>Altro</option>"
				+ "	<option value='glycan:ribo'>Ribo</option><option value='glycan:ido'>Ido</option>"
				+ "	<option value='glycan:threo'>Threo</option><option value='glycan:galacto'>Galacto</option>"
				+ "	<option value='glycan:gulo'>Gulo</option><option value='glycan:glycero'>Glycero</option>"
				+ "	</select> </span></td></tr><tr><td colspan='4'>Core Modification Information</td>"
			    + "</tr><tr><td>core modification type:</td><td><span><select id='has_core_modification_type' "
				+ "	style='width: 165px'><option selected  value=''>SELECT</option><option "
				+ "	value='glycan:core_modification_type_enx'>Enx</option><option  value='glycan:core_modification_type_deoxy'>"
				+ "Deoxy</option><option  value='glycan:core_modification_type_en'>En</option>"
				+ " <option  value='glycan:core_modification_type_yn'>Yn</option><option  value='glycan:core_modification_type_keto'>Keto</option>"  
				+ "	<option  value='glycan:core_modification_type_acid'>Acid</option><option  value='glycan:core_modification_type_sp'>SP</option>"
				+ "	<option  value='glycan:core_modification_type_geminal'>Geminal</option>"
				+ " <option  value='glycan:core_modification_type_sp2'>Sp2</option><option  value='glycan:core_modification_type_aldi'>Aldi</option>"
				+ "	<option  value='glycan:core_modification_type_anhydro'>Anhydro</option></select> </span></td>"
				+ "<td>modification position:</td><td><input type='text' id='has_modification_position' / ></td></tr>";
			var basetype = basetype1 + basetype2;
			$('#newBasetype').append(basetype);	
		}
		else if(marker%2==1)
		{
			$("#newBasetype").hide();	
			$("#inserNewBasetypeButton").val("Show New Basetype");
		}
		else if(marker%2==0)
		{
			$("#newBasetype").show();
			$("#inserNewBasetypeButton").val("Hide New Basetype");
		}
		marker++;
	}
</script>

</head>
<style type="text/css">
  #newBasetype
  {
  	border: 1px solid #ddd;
  }
  #newBasetype td
  {
  	border: 0px
  }
  #inserNewBasetypeButton
  {
  	margin-left:6px;
  }
</style>
<body>
<%@include file="navigation.jsp"%>

	<div class="row row-offcanvas row-offcanvas-right" id="navbarCollapse"
		class="collapse navbar-collapse">

		<div class="container">
			<div class="bs_example">
				<div></div>
				<h2>DB</h2>
			</div>
			<div class="bs-example">
			<h2>Monosaccharide DB</h2>
				
				<ul class="nav nav-tabs" id="myTab">
					<li><a data-toggle="tab" href="#sectionA" onclick="link_to_searchMono('<%=basePath%>')">Search</a></li>
					<li class="active"><a data-toggle="tab" href="#sectionB" >Insert</a></li>

				</ul>
				<div class="tab-content">

					<div id="sectionA" class="tab-pane fade  distance-re">
					</div>

					<div id="sectionB" class="tab-pane fade in active distance-re">
					<h3 style="margin-top:0px; margin-bottom:3px;">Insert</h3>	
				<table class="table table-condensed">

					<tbody>
						<tr>
							<td colspan="4">Monosaccharide Name:<input type="text"
								id="monosaccharide" />
								</td>
						</tr>

						<tr>
							<td colspan="4" ><span>Basetype: </span><span><select><option
											selected value="">Select</option>
							<%
											
											ArrayList<Basetype> basetypes = new ArrayList<Basetype>();
											basetypes = new Dao().getAllBasetype();
											if(basetypes.size() != 0){
												for (int i = 0; i < basetypes.size(); i++) {
													Basetype basetype = basetypes.get(i);
													String baseName = basetype.getBasetype().
															substring(basetype.getBasetype().indexOf("#") + 1);
											%>
											<option value="<%=baseName%>"><%=baseName%></option>
											<%
												}
											
											}
										%>				
											
											
											
											</select>
											 </span> <input type="button"
								class="btn btn-info" id="inserNewBasetypeButton" value="Insert New Basetype" onClick="insertNewBasetype()">
							</td>
						</tr>
						<tr>
							<td style="border-top:0px" colspan="4"><table id="newBasetype"></table></td>
						</tr>

						
						<tr>
							<td colspan="4"><h4>Property:</h4></td>
						</tr>
						<Tr>
							<Td>Average Molecular Weight:</Td>
							<Td><input type="text" id="has_average_molecular_weight" /></Td>
							<td>MSDB Id:</td>
							<td><input type="text" id="has_msdb_id" /></td>
						</Tr>		
						<tr>
							<td colspan="4"><h4>Substituent:</h4></td>
						</tr>

						<tr>
							<td>Substituent Name:</td>
							<td><input type="text" id="has_substituent" /></td>
							<td>Substituent Type:</td>
							<td>
							<span><select id="substituent_type"
									style="width: 165px">
										<option selected value="">SELECT</option>
										<option value="glycan:substituent_type_fluoro">F</option>
										<option value="glycan:substituent_type_thio">SH</option>
										<option value="glycan:substituent_type_s_methyl">SCH3</option>
										<option value="glycan:substituent_type_glycolyl">COCH2OH</option>
										<option value="glycan:substituent_type_trifluoroacetyl">COCF3</option>
										<option value="glycan:substituent_type_n_trimethyl">N(CH3)3</option>
										<option value="glycan:substituent_type_n_dimethyl">N(CH3)2</option>
										<option value="glycan:substituent_type_n_alanine">NHCOCHNH2CH3</option>
										<option value="glycan:substituent_type_iodo">I</option>
										<option value="glycan:substituent_type_chloro">Cl</option>
										<option value="glycan:substituent_type_s_pyruvate">CH2CCOOH</option>
										<option value="glycan:substituent_type_imino">NH</option>
										<option value="glycan:substituent_type_n_trifluoroacetyl">NHCOCF3</option>
										<option value="glycan:substituent_type_r_lactate">CH3CHCOOH</option>
										<option value="glycan:substituent_type_sulfate">SO3H</option>
										<option value="glycan:substituent_type_n_formyl">NHCHO</option>
										<option value="glycan:substituent_type_n_sulfate">NHSO3H</option>
										<option value="glycan:substituent_type_ethanolamine">NHCH2CH2OH</option>
										<option value="glycan:substituent_type_x_lactate">CH3CHCOOH</option>
										<option value="glycan:substituent_type_n_succinate">NCOCH2CH2COOH</option>
										<option value="glycan:substituent_type_ethyl">CH2CH3</option>
										<option value="glycan:substituent_type_n_methyl">NHCH3</option>
										<option value="glycan:substituent_type_telluro">TeH</option>
										<option value="glycan:substituent_type_nitrat">NO2</option>
										<option value="glycan:substituent_type_amino">NH2</option>
										<option value="glycan:substituent_type_s_lactate">CH3CHCOOH</option>
										<option value="glycan:substituent_type_n_glycolyl">NCOCH2OH</option>
										<option value="glycan:substituent_type_acetyl">COCH3</option>
										<option value="glycan:substituent_type_hydroxymethyl">CH2OH</option>
										<option value="glycan:substituent_type_seleno">SeH</option>
										<option value="glycan:substituent_type_formyl">CHO</option>
										<option value="glycan:substituent_type_x_pyruvate">CH2CCOOH</option>
										<option value="glycan:substituent_type_n_ethyl">NHCH2CH3</option>
										<option value="glycan:substituent_type_n_acetyl">NHCOCH3</option>
										<option value="glycan:substituent_type_r_pyruvate">CH2CCOOH</option>
										<option value="glycan:substituent_type_methyl">CH3</option>
										<option value="glycan:substituent_type_bromo">Br</option>
										<option value="glycan:substituent_type_phosphate">PO3H2</option>
								</select> </span>
							</td>
						</tr>
						<tr>
							<td>Linkage type:</td>
							<td>
								<span><select id="has_linkage_type"
									style="width: 165px">
									<option selected value="">SELECT</option>
										<option value="glycan:linkage_type_s_config">S Config</option>
										<option value="glycan:linkage_type_r_config">R Config</option>
										<option value="glycan:linkage_type_h_loss">H Loss</option>
										<option value="glycan:linkage_type_h_at_oh">H At OH</option>
										<option value="glycan:linkage_type_deoxy">Deoxy</option>
								</select> </span>
							</td>
							<td>Basetype Linkage Position:</td>
							<td><input type="text" id="has_basetype_linkage_position" /></td>
						</tr>

						<tr>
							<td colspan="4"><h4>Monosaccharide Alias:</h4></td>
						</tr>

						<tr>
							<td>Alias Name:</td>
							<td><input type="text" id="has_alias_name" /></td>
							<td><input type="checkbox" id="checkId"
								value="is_primary_name" checked />Primary Name</td>
							<td><input type="checkbox" id="checkId"
								value="is_trivial_name" />Trivial Name</td>
						</tr>
						<tr>
							<td>Monosaccharide Notation Scheme:</td>
							<td><span><select
									id="has_monosaccharide_notation_scheme" style="width: 165px">
									<option
											selected value="">SELECT</option>
									<option
											value="has_notation_scheme_pdb">PDB</option>
										<option value="has_notation_scheme_monosaccharide_db">MonosaccharideDB</option>
										<option value="has_notation_scheme_glycosciences_de">Glycosciences.de</option>
										<option value="has_notation_scheme_glycoct">GlycoCT</option>
										<option value="has_notation_scheme_cfg">CFG</option>
										<option value="has_notation_scheme_carbbank">CarbBank</option>
										<option value="has_notation_scheme_bcsdb">BCSDB</option>
										<option value="has_notation_scheme_amber_glycam">Amber
											Glycam</option></select> </span></td>
						</tr>
						<tr>
							<td colspan="4">
								<button type="button" style="margin-top:9px; height:45px; width:210px" class="btn btn-primary"
									onClick="submitMonosaccharideInfo('<%=basePath%>')">Submit
									Monosaccharide</button>
							</td>
						</tr>


					</tbody>
				</table>
				</div>
				</div>
				</div>
				</div>
				</div>
			
</body>
</html>