import request from '@/utils/request'

                    
// 分页查询运营商教练课时设置表列表
export function listPageCoachHourSettingDetail(query) {
  return request({
    url: '/admin/coachHourSettingDetail/pageList',
    method: 'post',
    params: query
  })
}


// 分页查询运营商教练课时设置表列表
export function listCoachHourSettingDetail(query) {
  return request({
    url: '/admin/coachHourSettingDetail/findList',
    method: 'post',
    params: query
  })
}

// 查询运营商教练课时设置表详细
export function getCoachHourSettingDetail(id) {
  return request({
    url: '/admin/coachHourSettingDetail/' + id,
    method: 'get'
  })
}

// 新增运营商教练课时设置表
export function addCoachHourSettingDetail(data) {
  return request({
    url: '/admin/coachHourSettingDetail',
    method: 'post',
    data: data
  })
}

// 修改运营商教练课时设置表
export function updateCoachHourSettingDetail(data) {
  return request({
    url: '/admin/coachHourSettingDetail',
    method: 'put',
    data: data
  })
}

// 删除运营商教练课时设置表
export function delCoachHourSettingDetail(id) {
  return request({
    url: '/admin/coachHourSettingDetail/' + id,
    method: 'delete'
  })
}


// 状态启用/停用运营商教练课时设置表
export function changeStatus(data) {
  return request({
    url: '/admin/coachHourSettingDetail/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出运营商教练课时设置表
export function exportCoachHourSettingDetail(query) {
  return request({
    url: '/admin/coachHourSettingDetail/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}