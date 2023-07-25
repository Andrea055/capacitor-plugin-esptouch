<script>
  import {
    Page,
    Navbar,
    NavTitle,
    Block,
    List,
    Button,
    ListInput,
    f7
  } from 'framework7-svelte';

  import { CapacitorWifi } from 'capacitorjs-plugin-wifi';
  import { EsptouchCapacitorPlugin } from 'capacitor-plugin-esptouch';

  let password = "", custom_data = "";

  async function checkPermissionResult() {
    let result = await CapacitorWifi.checkPermission();
    return result.status;
  }

  function request_permission() {
    let result = CapacitorWifi.requestPermission();
    f7.dialog.alert(result.status);
  }

  async function  run_esptouch() {
    if(!(await checkPermissionResult())) {
      request_permission();
      f7.dialog.alert("Run again please now that you have permission");
    }else {
      let result = await CapacitorWifi.getCurrentNetworkConfiguration();
    f7.dialog.alert(`Current wifi: SSID: ${result.ssid} BSSID: ${result.bssid}`);
    f7.dialog.alert("Starting ESPTouch V2");
    EsptouchCapacitorPlugin.EsptouchV2({
      ssid: result.ssid,
      bssid: result.bssid.toUpperCase(),
      password: password != "" ? password : null,
      custom_data: ""
    });
    };

  }

</script>

<Page name="home">
  <!-- Top Navbar -->
  <Navbar sliding={false}>
    <NavTitle>EspTouch V2 example</NavTitle>
  </Navbar>
  <!-- Page content -->
  <Block>
    <p>
      Here is a simple implementation of ESPTouchV2 using Framework7 and
      CapacitorJS
    </p>
  </Block>
  <List strongIos dividersIos insetIos>
    <ListInput label="Password" type="password" placeholder="Wi-Fi password" bind:value={password} clearButton>
      <i class="icon demo-list-icon" slot="media" />
    </ListInput>
    <ListInput label="Custom data" type="text" placeholder="Custom data" bind:value={custom_data} clearButton>
      <i class="icon demo-list-icon" slot="media" />
    </ListInput>
  </List>

  <Block>
    <Button fill on:click={run_esptouch}>Connect with ESPTouch V2</Button>
  </Block>
</Page>
